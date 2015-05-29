package com.releaser.collector.model.jaxb.api.xrel;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Uri
 */
@XmlRootElement
public class Uri
{
    /**
     * Contains name of Imdb platform
     */
    public static final String PLATFORM_IMDB = "imdb";

    /**
     * Contains char for splitting uri information
     */
    public static final String DELIMITER = ":";

    /**
     * Contains information about the platform and id
     *
     * e.g. imdb:tt12345
     */
    @XmlValue
    public String uri;

    /**
     * Returns the platform for the uri
     *
     * @return platform-name
     */
    public String getPlatform()
    {
        String platform = "";

        if (!uri.isEmpty()) {
            String[] parts = uri.split(Uri.DELIMITER);
            platform = parts[0];
        }

        return platform;
    }

    /**
     * Returns the id relating to the platform
     *
     * @return id of the platform
     */
    public String getId()
    {
        String id = "";

        if (!uri.isEmpty()) {
            String[] parts = uri.split(Uri.DELIMITER);
            //Setting id only when two elements were found
            id = (parts.length == 2) ? parts[1] : "";
        }

        return id;
    }
}
