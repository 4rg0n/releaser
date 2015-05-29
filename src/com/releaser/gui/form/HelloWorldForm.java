package com.releaser.gui.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by droenicke on 28.05.2015.
 */
public class HelloWorldForm extends JFrame implements ActionListener {
    private JButton clickMeButton;
    private JPanel rootPanel;
    private JTable table1;

    public HelloWorldForm()
    {
        super("Hello World!");

        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
        clickMeButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
