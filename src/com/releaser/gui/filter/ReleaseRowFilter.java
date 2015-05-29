package com.releaser.gui.filter;

import com.releaser.collector.model.jaxb.output.omdb.OutputOmdbModel;
import com.releaser.collector.release.Release;

import javax.swing.*;

/**
 * Created by droenicke on 29.05.2015.
 */
public class ReleaseRowFilter extends RowFilter
{
    private final String filterText;

    public ReleaseRowFilter(String filterText)
    {
        this.filterText = filterText;
    }

    @Override
    public boolean include(Entry entry)
    {
        if (filterText.isEmpty()) {
            return true;
        }

        //First column contains the release data
        Release release = (Release) entry.getValue(0);

        OutputOmdbModel omdbModel = release.getModel().omdb;

        //Title
        if (omdbModel.title.toLowerCase().contains(filterText.toLowerCase())) {
            return true;
        }

        //Genres
        for (String genre : omdbModel.genres) {
            if (genre.toLowerCase().contains(filterText.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
