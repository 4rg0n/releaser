package com.releaser.collector.model.jaxb.api.xrel;

import com.releaser.collector.model.jaxb.api.ApiModelInterface;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * XRelease Model
 */
@XmlRootElement(name = "release")
public class ApiXRelModel implements ApiModelInterface
{
    /**
     * Contains the xRel id to the release
     */
    @XmlElement(name = "id", required = true)
    public String id = "";

    /**
     * Contains the release name of the release
     *
     * e.g. American.Sniper.2014.German.DL.1080p.BluRay.x264-CONTRiBUTiON
     */
    @XmlElement(name = "dirname", required = true)
    public String dirname = "";

    /**
     * Contains the xRel link to the release
     */
    @XmlElement(name = "link_href", required = true)
    public String linkHref = "";

    /**
     * Contains the release date as timestamp
     */
    @XmlElement(name = "time", required = true)
    public Long time = 0L;

    /**
     * Contains the name of the release group
     *
     * e.g. CONTRiBUTiON
     */
    @XmlElement(name ="group_name", required = true)
    public String groupName = "";

    /**
     * Contains the reason why this release is eventually nuked (broken)
     */
    @XmlElement(name = "nuke_reason")
    public String nukeReason = "";

    /**
     * Contains the size information of the release
     */
    @XmlElement(name = "size")
    public Size size = null;

    /**
     * Contains the video type, if the release is a movie
     */
    @XmlElement(name = "video_type")
    public String videoType = "";

    /**
     * Contains the audio type, if the release is a movie
     */
    @XmlElement(name = "audio_type")
    public String audioType = "";

    /**
     * Contains the number of ratings which where given to this release
     */
    @XmlElement(name = "num_ratings")
    public Double numRatings = 0.0;

    /**
     * Contains the video rating, if the release is a movie
     */
    @XmlElement(name = "video_rating")
    public Double videoRating = 0.0;

    /**
     * Contains the audio rating, if the release is a movie
     */
    @XmlElement(name = "audio_rating")
    public Double audioRating = 0.0;

    /**
     * Contains external information to the release
     */
    @XmlElement(name = "ext_info", required = true)
    public ExtInfo extInfo = null;

    /**
     * Contains special flags (don't know for what...)
     */
    @XmlElementWrapper(name = "flags", required = true)
    @XmlElement(name = "flag")
    public List<String> flags = new ArrayList<>();

    /**
     * Returns whether the release is nuked (broken) or not
     *
     * @return nuked or not nuked
     */
    public Boolean isNuked()
    {
        return !nukeReason.isEmpty();
    }

    /**
     * Returns the release date of the release
     *
     * @todo bugged
     *
     * @return date object
     */
    public String getDate()
    {
        Date date = new Date(time);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * Returns the Imdb Id of release.
     * Returns empty string if there isn't a id.
     *
     * @return imdb id
     */
    public String getImdbId()
    {
        String imdbId = "";

        for (Uri uri : extInfo.uris) {
            if (uri.getPlatform().equals(Uri.PLATFORM_IMDB)) {
                imdbId = uri.getId();
                break;
            }
        }

        return imdbId;
    }

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
