package com.releaser.collector.model.jaxb.api.xrel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by droenicke on 09.06.2015.
 */
@XmlRootElement(name = "rate_limit_status")
public class ApiXrelRateLimitStatus
{
    @XmlElement(name = "remaining_calls")
    public int remainingCalls = 0;

    @XmlElement(name = "reset_time_u")
    public int resetTimeUnix = 0;

    @XmlElement(name = "reset_time")
    public String resetTime = "";

    public int getRemainingUnixTime()
    {
        int remainingTime = 0;

        if (resetTimeUnix != 0) {
            Timestamp currentTimeStamp = getCurrentTimeStamp();
            Timestamp resetTimeStamp = new Timestamp(resetTimeUnix);

            remainingTime = currentTimeStamp.compareTo(resetTimeStamp);

        }

        return remainingTime;
    }

    private Timestamp getCurrentTimeStamp()
    {
        Date date = new Date();

        return new Timestamp(date.getTime());
    }
}
