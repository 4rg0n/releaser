package com.releaser.collector.model.jaxb.output;

import com.releaser.collector.model.jaxb.api.xrel.ApiXRelModel;
import com.releaser.collector.model.jaxb.output.omdb.OutputOmdbModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ReleaserModel Output Model
 */
@XmlRootElement(name = "releaser")
public class ReleaserModel implements OutputModelInterface
{
    @XmlElement(name = "release")
    public ApiXRelModel release = null;

    @XmlElement(name = "omdb")
    public OutputOmdbModel omdb = null;

    public String toString()
    {
        String information = "";

        if (release != null) {
            information += release.toString();
        }

        if (omdb != null) {
            information += omdb.toString();
        }

        return information;
    }
}
