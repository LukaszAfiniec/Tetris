package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameInstruction extends JFrame {

	JPanel panel;
	
	public GameInstruction() {

		
		panel = new JPanel();
		setTitle("Instrukcja obs³ugi");
		setContentPane(new JLabel(new ImageIcon("src/main/resources/TetrisGameManual.png")));
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
		textfield1.setText("    Grê rozpoczynamy na pustej, prostok¹tnej "
				+ "planszy zwanej tetrionem lub matriksem, u³o¿onej krótszym bokiem w poziomie i maj¹cej wymiary 20 wierszy na 10 kolumn.");
		textfield1.setBounds(85,90, 940,20);
		textfield1.setBorder(BorderFactory.createEmptyBorder());
		textfield1.setBackground(new Color(224, 224, 224));
		add(textfield1);
		
		JTextField textfield2 = new JTextField();
		textfield2.setFont(font);
		textfield2.setEditable(false);
		textfield2.setText("W trakcie gry, poœrodku górnej krawêdzi"
				+ " planszy, pojawiaj¹ siê pojedynczo klocki z³o¿one z czterech ma³ych kwadratów nazywanych te¿ blokami. Klocki te ");
		textfield2.setBounds(85,110,940, 20);
		textfield2.setBorder(BorderFactory.createEmptyBorder());
		textfield2.setBackground( new Color(224, 224, 224));
		add(textfield2);
		
		JTextField textfield3 = new JTextField();
		textfield3.setFont(font);
		textfield3.setEditable(false);
		textfield3.setText("(okreœlane mianem „tetrimino przemieszczaj¹ siê w kierunku dolnej krawêdzi planszy (jeœli nie natrafi¹ na przeszkodê zn³o¿on¹ z innych klocków, które odpad³y wczeœniej).");
		textfield3.setBounds(85,130, 940, 20);
		textfield3.setBorder(BorderFactory.createEmptyBorder());
		textfield3.setBackground( new Color(224, 224, 224));
		add(textfield3);
		
		JTextField textfield4 = new JTextField();
		textfield4.setFont(font);
		textfield4.setEditable(false);
		textfield4.setText("Kiedy jedno tetrimino opadnie na samo dno, zostaje unieruchomione, a nastêpny klocek ukazuje siê u góry planszy. Gra trwa a¿ do momentu, w którym klocek nie bêdzie");
		textfield4.setBounds(85,150, 940, 20);
		textfield4.setBorder(BorderFactory.createEmptyBorder());
		textfield4.setBackground( new Color(224, 224, 224));
		add(textfield4);
		
		JTextField textfield5 = new JTextField();
		textfield5.setFont(font);
		textfield5.setEditable(false);
		textfield5.setText("móg³ pojawiæ siê na planszy.");
		textfield5.setBounds(85,170, 940, 20);
		textfield5.setBorder(BorderFactory.createEmptyBorder());
		textfield5.setBackground( new Color(224, 224, 224));
		add(textfield5);
		
		JTextField textfield6 = new JTextField();
		textfield6.setFont(font);
		textfield6.setEditable(false);
		textfield6.setText("    G³ównym zadaniem gracza jest uk³adanie tetrimino na planszy (poprzez wykorzystanie rotacji i przesuwanie klocków w poziomie) w taki sposób, aby kwadraty sk³adaj¹ce");
		textfield6.setBounds(85,190, 940, 20);
		textfield6.setBorder(BorderFactory.createEmptyBorder());
		textfield6.setBackground( new Color(224, 224, 224));
		add(textfield6);
		
		JTextField textfield7 = new JTextField();
		textfield7.setFont(font);
		textfield7.setEditable(false);
		textfield7.setText("siê na nie utworzy³y wiersz na ca³ej szerokoœci prostok¹ta. W takiej sytuacji wiersz ten zostaje usuniêty, a pozosta³e klocki opadaj¹ w kierunku dna, tworz¹c wiêcej ");
		textfield7.setBounds(85,210, 940, 20);
		textfield7.setBorder(BorderFactory.createEmptyBorder());
		textfield7.setBackground( new Color(224, 224, 224));
		add(textfield7);
		
		JTextField textfield8 = new JTextField();
		textfield8.setFont(font);
		textfield8.setEditable(false);
		textfield8.setText("przestrzeni dla nastêpnych elementów. Po usuniêciu okreœlonej liczby wierszy prêdkoœæ gry wzrasta w zale¿noœci od wybranego poziomu trudnoœci, co w znacz¹cy sposób ");
		textfield8.setBounds(85,230, 940, 20);
		textfield8.setBorder(BorderFactory.createEmptyBorder());
		textfield8.setBackground( new Color(224, 224, 224));
		add(textfield8);
		
		JTextField textfield9 = new JTextField();
		textfield9.setFont(font);
		textfield9.setEditable(false);
		textfield9.setText("utrudnia precyzyjne sterowanie kolejnymi tetrimino. ");
		textfield9.setBounds(85,250, 940, 20);
		textfield9.setBorder(BorderFactory.createEmptyBorder());
		textfield9.setBackground( new Color(224, 224, 224));
		add(textfield9);
		
		JTextField textfield10 = new JTextField();
		textfield10.setFont(font);
		textfield10.setEditable(false);
		textfield10.setText("Mo¿liwe jest jednoczesne usuniêcie maksymalnie 4 wierszy – umo¿liwia to tetrimino „I”. Sytuacja taka nosi nazwê identyczn¹ jak gra, czyli „tetris”");
		textfield10.setBounds(85,270, 940, 20);
		textfield10.setBorder(BorderFactory.createEmptyBorder());
		textfield10.setBackground( new Color(224, 224, 224));
		add(textfield10);
		
		
		JTextField textfield11 = new JTextField();
		textfield11.setFont(font2);
		textfield11.setEditable(false);
		textfield11.setText("Opis rozgrywki");
		textfield11.setBounds(85,70, 440,20);
		textfield11.setBorder(BorderFactory.createEmptyBorder());
		textfield11.setBackground(new Color(224, 224, 224));
		add(textfield11);
		
		JTextField textfield12 = new JTextField();
		textfield12.setFont(font2);
		textfield12.setEditable(false);
		textfield12.setText("Rodzaje Tetrimino");
		textfield12.setBounds(85,300, 400,20);
		textfield12.setBorder(BorderFactory.createEmptyBorder());
		textfield12.setBackground(new Color(224, 224, 224));
		add(textfield12);
		
		JTextField textfield13 = new JTextField();
		textfield13.setFont(font);
		textfield13.setEditable(false);
		textfield13.setText("    Na zestaw tetrimino sk³ada siê siedem ró¿nych (tj. takich które nie s¹ identyczne po wykonaniu rotacji) klocków z³o¿onych z kwadratowych elementów: ");
		textfield13.setBounds(85,320, 840,20);
		textfield13.setBorder(BorderFactory.createEmptyBorder());
		textfield13.setBackground(new Color(224, 224, 224));
		add(textfield13);
	}
}

