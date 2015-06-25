package com.releaser.gui.javafx.model.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for Release table
 */
public class ReleaseTableModel
{
    private final StringProperty name;

    public ReleaseTableModel(String name)
    {
        this.name = new SimpleStringProperty(name);
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }
}
