package com.tetris.ui;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class SplashScreen extends JFrame{
	
    private JWindow window;
    private JPanel panel;
    private long startTime;
    private int minimumMilliseconds;
    private JLabel image;
   
    
    public SplashScreen() {
    
        window = new JWindow();
        panel = new JPanel();
        image = new JLabel(new ImageIcon("src/main/resources/TetrisLogo.png"));
         
        window.setSize(530,490);
        window.setLocationRelativeTo(null);
        
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        image.setSize(530,490);
        panel.add(image);
        
        window.add(panel);
 
    }

    //Przerobic na proste int milisec bez kombinowania
    public void show(int minimumMilliseconds) {
        this.minimumMilliseconds = minimumMilliseconds;
        window.setVisible(true);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void hide() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.dispose();
        window.setVisible(false);
    }
    
    
}
