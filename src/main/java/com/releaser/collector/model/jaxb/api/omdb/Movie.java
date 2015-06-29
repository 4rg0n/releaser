package com.releaser.collector.model.jaxb.api.omdb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Movie Model
 */
@XmlRootElement(name = "movie")
public class Movie
{
    @XmlAttribute(name = "title")
    public String title = "";

    @XmlAttribute(name = "year")
    public Integer year = 0;

    @XmlAttribute(name = "rated")
    public String rated = "";

    @XmlAttribute(name = "released")
    public String released = "";

    @XmlAttribute(name = "runtime")
    public String runtime = "";

    @XmlAttribute(name = "genre")
    public String genre = "";

    @XmlAttribute(name = "director")
    public String director = "";

    @XmlAttribute(name = "writer")
    public String writer = "";

    @XmlAttribute(name = "actors")
    public String actors = "";

    @XmlAttribute(name = "plot")
    public String plot = "";

    @XmlAttribute(name = "language")
    public String language = "";

    @XmlAttribute(name = "country")
    public String country = "";

    @XmlAttribute(name = "poster")
    public String poster = "";

    @XmlAttribute(name = "metascore")
    public Integer metascore = 0;

    @XmlAttribute(name = "imdbRating")
    public Double imdbRating = 0.0;

    @XmlAttribute(name = "imdbVotes")
    public String imdbVotes = "";

    @XmlAttribute(name = "imdbID")
    public String imdbID = "";

    @XmlAttribute(name = "type")
    public String type = "";
}
