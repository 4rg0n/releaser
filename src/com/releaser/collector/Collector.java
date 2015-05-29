package com.releaser.collector;

import com.releaser.collector.apiclient.OmdbClient;
import com.releaser.collector.apiclient.XRelClient;
import com.releaser.collector.file.Reader;
import com.releaser.collector.release.Release;
import com.releaser.collector.release.ReleaseInterface;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Collector
 */
public class Collector
{
    public static void collect(String path)
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
                    //TODO Logger
                    System.out.println("Skipping Release '" + release.getName() + "'");
                    System.out.println("Reason: " + e.getMessage());
                }
            }

            //Save releaser files
            for (ReleaseInterface release : releases) {
                try {
                    release.save();
                } catch (IOException|RuntimeException e) {
                    //TODO Logger
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            //TODO Logger
            System.out.println(e.getMessage());
        }
    }
}
