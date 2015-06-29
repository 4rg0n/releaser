package com.releaser.collector.model.jaxb.api.xrel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * External information to a release
 */
@XmlRootElement
public class ExtInfo
{
    /**
     * Contains the type of a release
     *
     * e.g. movie, game
     */
    @XmlElement(name = "type")
    public String type = "";

    /**
     * Contains the xRel id of the specific game or movie
     */
    @XmlElement(name = "id")
    public String id = "";

    /**
     * Contains the name of the movie or game
     */
    @XmlElement(name = "title")
    public String title = "";

    /**
     * Contains the xRel link of the specific game or movie
     */
    @XmlElement(name = "link_href")
    public String linkHref = "";

    /**
     * Contains the xRel rating of the specific game or movie
     */
    @XmlElement(name = "rating")
    public Double rating = 0.0;

    /**
     * Contains the number of ratings for that specific game or movie
     */
    @XmlElement(name = "num_ratings")
    public Integer numRatings = 0;

    /**
     * Contains additional uris for more info (e.g. imdb)
     */
    @XmlElementWrapper(name = "uris")
    @XmlElement(name = "uri")
    public List<Uri> uris = new ArrayList<>();
}
