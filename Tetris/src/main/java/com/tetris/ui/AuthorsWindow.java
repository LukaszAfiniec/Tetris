package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AuthorsWindow extends JFrame {
	
	public AuthorsWindow() {
		
		setTitle("Autorzy");
		setContentPane(new JLabel(new ImageIcon("src/main/resources/AuthorsWindowGraphic.png")));
		setLayout(null);
		setSize(715, 420);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(false);
		
	
		Font font = new Font("Arial", Font.PLAIN, 23);
		
		JTextField textfield1 = new JTextField();
		textfield1.setFont(font);
		textfield1.setEditable(false);
		textfield1.setText(" Projekt Tetris wykonali:");
		textfield1.setBounds(220,95, 260,40);
		textfield1.setBorder(BorderFactory.createEmptyBorder());
		textfield1.setBackground(new Color(224, 224, 224));
		add(textfield1);
		
		JTextField textfield2 = new JTextField();
		textfield2.setFont(font);
		textfield2.setEditable(false);
		textfield2.setText(" * £ukasz Afiniec - lukasz130391@gmail.com");
		textfield2.setBounds(105,165, 480, 40);
		textfield2.setBorder(BorderFactory.createEmptyBorder());
		textfield2.setBackground( new Color(224, 224, 224));
		add(textfield2);
		
		JTextField textfield3 = new JTextField();
		textfield3.setFont(font);
		textfield3.setEditable(false);
		textfield3.setText(" * Taras Kushnir - taras.kushnir.tk@gmail.com");
		textfield3.setBounds(105,225, 480, 40);
		textfield3.setBorder(BorderFactory.createEmptyBorder());
		textfield3.setBackground( new Color(224, 224, 224));
		add(textfield3);
		
	
	}
	
}
