package com.releaser.collector.model.jaxb.api.omdb;

import com.releaser.collector.model.jaxb.api.ApiModelInterface;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Omdb Model
 *
 * @link http://www.omdbapi.com
 */
@XmlRootElement(name = "root")
public class ApiOmdbModel implements ApiModelInterface
{
    @XmlElement(name = "movie")
    public Movie movie = null;
}
