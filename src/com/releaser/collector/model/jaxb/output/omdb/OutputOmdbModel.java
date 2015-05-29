package com.releaser.collector.model.jaxb.output.omdb;


import com.releaser.collector.model.jaxb.output.OutputModelInterface;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * XML Omdb Model for output
 */
@XmlRootElement(name = "omdb")
public class OutputOmdbModel implements OutputModelInterface
{
    @XmlElement(name = "title")
    public String title = "";

    @XmlElement(name = "year")
    public Integer year = 0;

    @XmlElement(name = "rated")
    public String rated = "";

    @XmlElement(name = "released")
    public String released = "";

    @XmlElement(name = "runtime")
    public String runtime = "";

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    public List<String> genres = new ArrayList<>();

    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    public List<String> directors = new ArrayList<>();

    @XmlElementWrapper(name = "writers")
    @XmlElement(name = "writer")
    public List<String> writers = new ArrayList<>();

    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    public List<String> actors = new ArrayList<>();

    @XmlElement(name = "plot")
    public String plot = "";

    @XmlElement(name = "language")
    public String language = "";

    @XmlElement(name = "country")
    public String country = "";

    @XmlElement(name = "poster")
    public String poster = "";

    @XmlElement(name = "metascore")
    public Integer metascore = 0;

    @XmlElement(name = "imdbRating")
    public Double imdbRating = 0.0;

    @XmlElement(name = "imdbVotes")
    public String imdbVotes = "";

    @XmlElement(name = "imdbID")
    public String imdbID = "";

    @XmlElement(name = "type")
    public String type = "";

    public String toString()
    {
        String information = "";
        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                String value = field.get(this).toString();

                information += "<b>" + field.getName() + ":</b> " + value + "<br/>";
            } catch (IllegalAccessException|IllegalArgumentException e) {
                continue;
            }
        }

        return information;
    }
}
