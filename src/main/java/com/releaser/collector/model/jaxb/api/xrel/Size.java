package com.releaser.collector.model.jaxb.api.xrel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Size Model
 */
@XmlRootElement
public class Size
{
    /**
     *
     */
    @XmlElement(name = "number")
    public Integer number = 0;

    @XmlElement(name = "unit")
    public String unit = "";
}
