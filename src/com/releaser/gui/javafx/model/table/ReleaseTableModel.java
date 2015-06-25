package com.releaser.gui.javafx.model.table;

import javafx.beans.property.*;

/**
 * Model for Release table
 */
public class ReleaseTableModel
{
    private final StringProperty name;

    private final StringProperty title;

    private final StringProperty imdbRating;

    public ReleaseTableModel(String name, String title, Double imdbRating)
    {
        this.name = new SimpleStringProperty(name);
        this.title = new SimpleStringProperty(title);
        this.imdbRating = new SimpleStringProperty(imdbRating.toString());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getImdbRating() {
        return imdbRating.get();
    }

    public StringProperty imdbRatingProperty() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating.set(imdbRating);
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating.set(imdbRating.toString());
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
}
