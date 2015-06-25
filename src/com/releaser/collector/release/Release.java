package com.releaser.collector.release;

import com.releaser.collector.apiclient.OmdbClient;
import com.releaser.collector.apiclient.XRelClient;
import com.releaser.collector.converter.OmdbConverter;
import com.releaser.collector.exception.ApiClientException;
import com.releaser.collector.model.jaxb.api.omdb.ApiOmdbModel;
import com.releaser.collector.model.jaxb.api.xrel.ApiXRelModel;
import com.releaser.collector.model.jaxb.output.ReleaserModel;
import com.releaser.collector.model.jaxb.output.omdb.OutputOmdbModel;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Contains information about a release
 */
public class Release implements ReleaseInterface
{
    /**
     * Contains the filename for saving the information
     */
    public static final String FILENAME = "releaser.xml";

    /**
     * Contains information about the folder
     */
    private File file;

    /**
     * Contains the information from XRel
     */
    private ApiXRelModel xRelModel = null;

    /**
     * Contains the information from omdb
     */
    private ApiOmdbModel apiOmdbModel = null;

    /**
     * Contains all information about the release
     */
    private ReleaserModel model = null;

    /**
     * Constructor
     *
     * @param file object
     */
    public Release(final File file)
    {
        this.file = file;
    }

    /**
     * Reads information
     *
     * @param xRelClient xrel api client
     */
    public void hibernateRelease(XRelClient xRelClient) throws RuntimeException
    {
        try {
            xRelModel = xRelClient.read(this);
        } catch (ApiClientException apiClientException) {
            throw new RuntimeException(
                    "Could not hibernate Release '" + this.getName() + "' with release information.",
                    apiClientException
            );
        }
    }

    /**
     * Reads information
     *
     * @param omdbClient imdb api client
     */
    public void hibernateOmdb(OmdbClient omdbClient) throws RuntimeException
    {
        if (xRelModel == null) {
            throw new RuntimeException("No release information for release '" + this.getName() + "'");
        }

        String imdbId = xRelModel.getImdbId();

        if (imdbId.isEmpty()) {
            throw new RuntimeException("Release '" + this.getName() + "' has no Imdb Id.");
        }
        try {
            apiOmdbModel = omdbClient.read(this);
        } catch (ApiClientException apiClientException) {
            throw new RuntimeException(
                    "Could not hibernate Release '" + this.getName() + "' with omdb information.",
                    apiClientException
            );
        }
    }

    /**
     * Fills release class with information from file
     *
     * @return true if a new model was read, false if model already exists
     */
    public boolean hibernateFile() throws FileNotFoundException
    {
        //Does model already exists in class?
        if (model != null) {
            return false;
        }

        String path = this.file.getPath() + File.separator + Release.FILENAME;
        File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException("File '" + path +"' does not exist");
        }

        model = JAXB.unmarshal(file, ReleaserModel.class);

        return true;
    }

    /**
     * Returns the release information from api
     *
     * @return xrel model
     */
    public ApiXRelModel getXRelModel()
    {
        return xRelModel;
    }

    /**
     * Retorns the omdb information from api
     *
     * @return omdb model
     */
    public ApiOmdbModel getOmdbModel()
    {
        return apiOmdbModel;
    }

    /**
     * Returns the releaser model
     *
     * @return releaser modekl
     */
    public ReleaserModel getModel()
    {
        return model;
    }

    /**
     * Returns the file object (directory)
     *
     * @return file object
     */
    public File getFile()
    {
        return file;
    }

    /**
     * Returns the name of the release
     *
     * @return release name
     */
    public String getName()
    {
        return file.getName();
    }

    public boolean update()
    {
        return true;
    }

    /**
     *
     * @return success
     * @throws IOException|RuntimeException
     */
    public boolean save() throws IOException, RuntimeException
    {
        if (apiOmdbModel == null && xRelModel == null) {
            throw new RuntimeException("Release '" + getName() + "' has no data to save");
        }

        String savePath = file.getPath() + File.separator + Release.FILENAME;
        File releaserFile = new File(savePath);

        //Skip file if already exists
        if (releaserFile.exists()) {
            return false;
        }

        ReleaserModel model = new ReleaserModel();
        OmdbConverter omdbConverter = new OmdbConverter(
                apiOmdbModel,
                new OutputOmdbModel()
        );

        model.release = xRelModel;
        model.omdb = omdbConverter.convert();

        //Save model in class
        this.model = model;

        if (releaserFile.createNewFile()) {
            //Populate file with XML stuff
            JAXB.marshal(model, releaserFile);
        }

        return true;
    }

    /**
     * Deletes the releaser file in the release folder
     * Returns if the file could be deleted or not
     *
     * @return success
     */
    public boolean delete() throws IOException
    {
        String releaserFilePath = file.getPath() + File.separator + Release.FILENAME;
        File releaserFile = new File(releaserFilePath);

        if (!releaserFile.exists()) {
            return false;
        }

        if (!releaserFile.delete()) {
            throw new IOException("Could not delete file '" + releaserFilePath + "'");
        }

        return true;
    }

    /**
     * Checks whether the release has a releaser file
     *
     * @return true if releaser file exists else false
     */
    public boolean hasReleaserFile()
    {
        String path = file.getPath() + File.separator + Release.FILENAME;
        File releaserFile = new File(path);

        return releaserFile.exists();
    }

    /**
     * Returns the name of the release
     * Used for displaying release in a JTable
     *
     * @return name of the release
     */
    public String toString()
    {
        return getName();
    }
}
