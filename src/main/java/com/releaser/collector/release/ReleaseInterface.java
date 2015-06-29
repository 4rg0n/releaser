package com.releaser.collector.release;

import com.releaser.collector.model.jaxb.api.xrel.ApiXRelModel;

import java.io.File;
import java.io.IOException;

/**
 * Created by droenicke on 22.05.2015.
 */
public interface ReleaseInterface
{
    /**
     * Returns the release name
     *
     * @return release name
     */
    public String getName();

    public File getFile();

    public boolean update() throws IOException;

    public boolean save() throws IOException;

    public ApiXRelModel getXRelModel();
}
