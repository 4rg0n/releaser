package com.releaser.collector.file;

import com.releaser.collector.release.Release;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads Sub-Directories of a directory
 */
public class Reader
{
    private Path path = null;

    private final ArrayList<Release> releases = new ArrayList<>();

    public static final String RELEASE_IDENTIFIER = ".";

    /**
     * Constructor
     *
     * @param path path to folder
     */
    public Reader(final Path path)
    {
        this.path = path;
    }

    /**
     * Returns the path
     *
     * @return path to folder
     */
    public Path getPath()
    {
        return path;
    }

    /**
     * Reads directories in configured path
     *
     * @return list of files
     */
    public ArrayList<Release> read() throws IOException
    {
        File folder = path.toFile();

        if (!folder.exists()) {
            throw new IOException("Folder '" + path + "' does not exist.");
        }

        listReleases(path.toFile());

        return releases;
    }

    /**
     * Lists all releases in given directory recursively
     *
     * @param directory directory to search in
     */
    private void listReleases(File directory)
    {
        //Get list of all files and folders in directory
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        //For all files and folders in directory
        for (File file : files) {
            //Check if directory
            if (file.isDirectory()) {

                if (file.getName().contains(RELEASE_IDENTIFIER)) {
                    releases.add(new Release(file));
                }

                //Recursively call file list function on the new directory
                listReleases(file);
            }
        }
    }
}
