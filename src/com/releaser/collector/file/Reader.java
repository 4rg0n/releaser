package com.releaser.collector.file;

import com.releaser.collector.release.Release;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Reads Sub-Directories of a directory
 */
public class Reader
{
    private Path path = null;

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

        File[] fileList = folder.listFiles();
        ArrayList<Release> list = new ArrayList<>();

        if (fileList == null) {
            return list;
        }

        for (File file : fileList) {
            Release release = new Release(file);
            list.add(release);
        }

        return list;
    }
}
