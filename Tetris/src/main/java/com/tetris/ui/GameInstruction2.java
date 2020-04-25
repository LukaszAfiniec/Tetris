package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameInstruction2 extends JFrame {

	JPanel panel;

	public GameInstruction2() {

		panel = new JPanel();
		setTitle("Instrukcja sterowania w grze");
		setContentPane(new JLabel(new ImageIcon("src/main/resources/TetrisGameManual2.png")));
		setLayout(null);
		setSize(1125, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(false);

		Font font = new Font("Arial", Font.PLAIN, 12);
		Font font2 = new Font("Arial Black", Font.PLAIN, 14);

		JTextField textfield1 = new JTextField();
		textfield1.setFont(font);
		textfield1.setEditable(false);
		textfield1.setText("Do przemieszczania siê klockiem w lewo i w prawo s³u¿¹ strza³ki na klawiaturze oznaczone     <   oraz   >   ");
		textfield1.setBounds(85, 90, 600, 20);
		textfield1.setBorder(BorderFactory.createEmptyBorder());
		textfield1.setBackground(new Color(224, 224, 224));
		add(textfield1);

		
		JTextField textfield2 = new JTextField();
		textfield2.setFont(font);
		textfield2.setEditable(false);
		textfield2.setText("Do obracania siê klockiem w lewo i w prawo s³u¿¹ strza³ki na klawiaturze oznaczone jako        /\\   oraz   \\/   ");
		textfield2.setBounds(85, 150, 600, 20);
		textfield2.setBorder(BorderFactory.createEmptyBorder());
		textfield2.setBackground(new Color(224, 224, 224));
		add(textfield2);


		JTextField textfield7 = new JTextField();
		textfield7.setFont(font);
		textfield7.setEditable(false);
		textfield7.setText("Wciœniêcie klawisza Spacji powoduje natychmiastowe œci¹gniecie klocka na sam dó³ planszy");
		textfield7.setBounds(85, 235, 540, 20);
		textfield7.setBorder(BorderFactory.createEmptyBorder());
		textfield7.setBackground(new Color(224, 224, 224));
		add(textfield7);


		JTextField textfield10 = new JTextField();
		textfield10.setFont(font);
		textfield10.setEditable(false);
		textfield10.setText("Klawisz D s³u¿y do przyspieszania opadania klocka");
		textfield10.setBounds(85, 350, 540, 20);
		textfield10.setBorder(BorderFactory.createEmptyBorder());
		textfield10.setBackground(new Color(224, 224, 224));
		add(textfield10);

		JTextField textfield11 = new JTextField();
		textfield11.setFont(font2);
		textfield11.setEditable(false);
		textfield11.setText("Opis klawiszy odpowiadaj¹cych za sterowanie w grze");
		textfield11.setBounds(85, 62, 440, 15);
		textfield11.setBorder(BorderFactory.createEmptyBorder());
		textfield11.setBackground(new Color(224, 224, 224));
		add(textfield11);

		JTextField textfield12 = new JTextField();
		textfield12.setFont(font2);
		textfield12.setEditable(false);
		textfield12.setText("Opis przycisków w g³ównym oknie gry");
		textfield12.setBounds(85, 410, 480, 18);
		textfield12.setBorder(BorderFactory.createEmptyBorder());
		textfield12.setBackground(new Color(224, 224, 224));
		add(textfield12);

		JTextField textfield13 = new JTextField();
		textfield13.setFont(font);
		textfield13.setEditable(false);
		textfield13.setText("Start - rozpoczyna lub wznawia grê      Pause - zatrzymuje rozgrywkê      Stop - koñczy rozpoczêt¹ grê");
		textfield13.setBounds(85, 440, 580, 20);
		textfield13.setBorder(BorderFactory.createEmptyBorder());
		textfield13.setBackground(new Color(224, 224, 224));
		add(textfield13);
	}
}
