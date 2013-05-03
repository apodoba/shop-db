package com.apodoba.desktop;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("Shop client");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initControls();

        this.pack();
        this.setVisible(true);
    }

    private void initControls() {
        
    }
}
