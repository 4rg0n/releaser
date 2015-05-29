package com.releaser.gui.model.table;

import javax.swing.table.DefaultTableModel;

/**
 * Release Table Model
 */
public class ReleaseTableModel extends DefaultTableModel
{
    /**
     * Disables editing for all cells
     *
     * @param row row number
     * @param column column number
     * @return editable or not
     */
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
}
