package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WinnerWindow extends JFrame implements ActionListener {

	String userNameString = "";
	JButton okButton;
	JTextField jtextField ;
	
	public WinnerWindow(String gameLevelHard, int score) {
	setTitle("!!! GRATULACJE WYNIKU !!!");
	setBackground(new Color(224, 224, 224));
	setLayout(null);
	setSize(550, 350);
	setResizable(false);
	setLocationRelativeTo(null);
	setFocusable(false);
	getContentPane().setLayout(null);
	setLocationRelativeTo(null);
	
	JLabel lblName = new JLabel("Podaj swój nick:");
	lblName.setFont(new Font("Arial", Font.BOLD, 15));
	lblName.setBounds(70, 70, 150, 20);
	lblName.setBorder(BorderFactory.createEmptyBorder());
	lblName.setBackground(new Color(224, 224, 224));
	getContentPane().add(lblName);
	
	jtextField = new JTextField();
	jtextField.setBounds(210, 70, 150, 20);
	jtextField.setBorder(BorderFactory.createEmptyBorder());
	getContentPane().add(jtextField);
	
	
	okButton = new JButton("OK");
	okButton.setBounds(180, 130, 100, 50);
	okButton.addActionListener(this);
	getContentPane().add(okButton);
	
	}
	
	@Override
 	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		 
 		if(source == okButton) {
 			userNameString = jtextField.getText();
 			
 			
 			
 			
 		}
 			
	}
	
}
