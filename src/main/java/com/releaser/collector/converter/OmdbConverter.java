package com.releaser.collector.converter;

import com.releaser.collector.model.jaxb.api.omdb.ApiOmdbModel;
import com.releaser.collector.model.jaxb.api.omdb.Movie;
import com.releaser.collector.model.jaxb.output.omdb.OutputOmdbModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Omdb Converter
 */
public class OmdbConverter
{
    /**
     * Filled api omdb model
     */
    private ApiOmdbModel apiOmdbModel;

    /**
     * Empty output omdb model
     */
    private OutputOmdbModel outputModel;

    /**
     * Constructor
     *
     * @param apiOmdbModel api omdb model with data
     * @param outputModel empty outut omdb model
     */
    public OmdbConverter(final ApiOmdbModel apiOmdbModel, final OutputOmdbModel outputModel)
    {
        this.apiOmdbModel = apiOmdbModel;
        this.outputModel = outputModel;
    }

    /**
     * Reads data from api omdb model and maps it into the output omdb model
     *
     * @return mapped output omdb model
     */
    public OutputOmdbModel convert()
    {
        Movie omdbMovie = apiOmdbModel.movie;

        outputModel.title = omdbMovie.title;
        outputModel.year = omdbMovie.year;
        outputModel.country = omdbMovie.country;
        outputModel.language = omdbMovie.language;
        outputModel.metascore = omdbMovie.metascore;
        outputModel.plot = omdbMovie.plot;
        outputModel.poster = omdbMovie.poster;
        outputModel.type = omdbMovie.type;
        outputModel.rated = omdbMovie.rated;
        outputModel.released = omdbMovie.released;
        outputModel.runtime = omdbMovie.runtime;

        outputModel.writers = this.extractList(omdbMovie.writer);
        outputModel.directors = this.extractList(omdbMovie.director);
        outputModel.genres = this.extractList(omdbMovie.genre);
        outputModel.actors = this.extractList(omdbMovie.actors);

        outputModel.imdbID = omdbMovie.imdbID;
        outputModel.imdbRating = omdbMovie.imdbRating;
        outputModel.imdbVotes = omdbMovie.imdbVotes;

        return outputModel;
    }

    /**
     * Extracts individual names of a sring.
     * Names are seperated with comma ", "
     *
     * @param string multiple names seperated with comma
     * @return list of names
     */
    private List<String> extractList(String string)
    {
        String[] extractedList = string.split(", ");
        List<String> list = new ArrayList<>();

        for (String entry : extractedList) {
            list.add(entry);
        }

        return list;
    }
}
