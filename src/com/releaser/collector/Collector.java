package com.releaser.collector;

import com.releaser.collector.apiclient.OmdbClient;
import com.releaser.collector.apiclient.XRelClient;
import com.releaser.collector.exception.CollectorException;
import com.releaser.collector.file.Reader;
import com.releaser.collector.release.Release;
import com.releaser.collector.release.ReleaseInterface;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Collector
 */
public class Collector
{
    private Logger logger = Logger.getLogger(Collector.class.getName());

    /**
     * Collects information from each subfolder in given path
     *
     * @param path folder path
     */
    public void collect(String path) throws CollectorException
    {
        Reader reader = new Reader(Paths.get(path));
        XRelClient xRelClient = new XRelClient();
        OmdbClient omdbClient = new OmdbClient();

        try {
            ArrayList<Release> releases = reader.read();

            //Hibernate releases with api data
            for (Release release : releases) {
                try {
                    release.hibernateRelease(xRelClient);
                    release.hibernateOmdb(omdbClient);
                } catch (RuntimeException e) {
                    logger.info("Skipping Release '" + release.getName() + "'. Reason: " + e.getMessage());
                }
            }

            //Save releaser files
            for (ReleaseInterface release : releases) {
                try {
                    release.save();
                } catch (IOException|RuntimeException e) {
                    logger.warning("Unable to save releaser file in " + release.getFile().getPath());
                }
            }

        } catch (IOException e) {
            throw new CollectorException(
                    "Could not read releases",
                    e
            );
        }
    }

    /**
     * Deletes all collected information (releaser.xml) in subdirectories in given path
     *
     * @param path folder path
     */
    public void clean(String path) throws CollectorException
    {
        Reader reader = new Reader(Paths.get(path));

        try {
            ArrayList<Release> releases = reader.read();

            for (Release release : releases) {

                try {
                    release.delete();
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new CollectorException(
                    "Could not read releases",
                    e
            );
        }
    }

    /**
     * Returns the logger
     *
     * @return logger instance
     */
    public Logger getLogger()
    {
        return logger;
    }
}
