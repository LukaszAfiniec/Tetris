package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.tetris.implementation.FileManager;

public class HighScoreWindow extends JFrame {
	private JScrollPane scrollPane = new JScrollPane();
	String[] splitted;
	String[] tabelaGraczyString = new String[91];

	public HighScoreWindow(int poziomTrudnosci) {
		loadUserScoreFromFile();
		new FileManager();
		
		Font font = new Font("Arial", Font.PLAIN, 32);
		Font font2 = new Font("Arial Black", Font.PLAIN, 26);
		Font font3 = new Font("Arial", Font.PLAIN, 26);
		
		if (poziomTrudnosci == 1) {
			setTitle("Top 10 graczy - poziom trudnoœci: ³atwy");
		} else if (poziomTrudnosci == 2) {
			setTitle("Top 10 graczy - poziom trudnoœci: œredni");
		} else if (poziomTrudnosci == 3) {
			setTitle("Top 10 graczy - poziom trudnoœci: trudny");
		}
		setContentPane(new JLabel(new ImageIcon("src/main/resources/HighScoreTheme.png")));
		setLayout(null);
		setSize(1000, 730);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(false);
		

		JTextField textfield0 = new JTextField();
		textfield0.setEditable(false);
		textfield0.setFont(font2);
		textfield0.setText("Poziom trudnoœci:");
		textfield0.setBounds(140, 420, 300, 26);
		textfield0.setBorder(BorderFactory.createEmptyBorder());
		textfield0.setBackground(new Color(224, 224, 224));
		add(textfield0);
		
		JTextField textfield1 = new JTextField();
		textfield1.setEditable(false);
		textfield1.setFont(font3);
		if (poziomTrudnosci == 1) {
			textfield1.setText("Latwy");
		} else if (poziomTrudnosci == 2) {
			textfield1.setText("Sredni");
		} else if (poziomTrudnosci == 3) {
			textfield1.setText("Trudny");
		}
		
		textfield1.setBounds(140, 450, 200, 26);
		textfield1.setBorder(BorderFactory.createEmptyBorder());
		textfield1.setBackground(new Color(224, 224, 224));
		add(textfield1);
	

		JTextField textfield2 = new JTextField();
		textfield2.setFont(font);
		textfield2.setEditable(false);
		textfield2.setText("Lista zwyciêzców:");
		textfield2.setBounds(380, 140, 300, 33);
		textfield2.setBorder(BorderFactory.createEmptyBorder());
		textfield2.setBackground(new Color(224, 224, 224));
		add(textfield2);
		
		
		scrollPane.setBounds(280, 190, 450, 180);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(new Color(224, 224, 224));
		scrollPane.setViewportView(tableUserScoreCreate(3));
		
		add(scrollPane);
	}

	public void loadUserScoreFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("userhighscore.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				splitted = strLine.split("#");

				for (int i = 0; i < splitted.length; i++)
					 tabelaGraczyString[i] = splitted[i] ;
			}
			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	
	public JTable tableUserScoreCreate(int poziomTrudnoœci) {
		
		String[] columnNames = {"Miejsce", "Nazwa gracza", "Iloœæ zdobytych punktów" };
		JTable table = null;
		
		
		if(poziomTrudnoœci==1) {
			Object[][] data = { 
			  {1, tabelaGraczyString[2], tabelaGraczyString[3] },
			  {2, tabelaGraczyString[5], tabelaGraczyString[6] },
			  {3, tabelaGraczyString[8], tabelaGraczyString[9] },
			  {4, tabelaGraczyString[11], tabelaGraczyString[12]},
			  {5, tabelaGraczyString[14], tabelaGraczyString[15]}, 
			  {6, tabelaGraczyString[17], tabelaGraczyString[18]},
			  {7, tabelaGraczyString[20], tabelaGraczyString[21]}, 
			  {8, tabelaGraczyString[23], tabelaGraczyString[24]},
			  {9, tabelaGraczyString[26], tabelaGraczyString[27]},
			  {10, tabelaGraczyString[29], tabelaGraczyString[30]}
			};
			table = new JTable(data, columnNames);
            table.setDefaultEditor(Object.class, null);
			table.setBounds(200, 130, 510, 410);
			table.setBorder(BorderFactory.createEmptyBorder());
			table.setBackground(new Color(224, 224, 224));
			return table;
		} else if(poziomTrudnoœci==2) {
			Object[][] data = { 
					  {1, tabelaGraczyString[32], tabelaGraczyString[33] },
					  {2, tabelaGraczyString[35], tabelaGraczyString[36] },
					  {3, tabelaGraczyString[38], tabelaGraczyString[39] },
					  {4, tabelaGraczyString[41], tabelaGraczyString[42]},
					  {5, tabelaGraczyString[44], tabelaGraczyString[45]}, 
					  {6, tabelaGraczyString[47], tabelaGraczyString[48]},
					  {7, tabelaGraczyString[50], tabelaGraczyString[51]}, 
					  {8, tabelaGraczyString[53], tabelaGraczyString[54]},
					  {9, tabelaGraczyString[56], tabelaGraczyString[57]},
					  {10, tabelaGraczyString[59], tabelaGraczyString[60]}
					};
					table = new JTable(data, columnNames);
					table.setDefaultEditor(Object.class, null);
					table.setBounds(200, 130, 510, 410);
					table.setBorder(BorderFactory.createEmptyBorder());
					table.setBackground(new Color(224, 224, 224));
					return table;
				} else if(poziomTrudnoœci==3) {
					Object[][] data = { 
							  {1, tabelaGraczyString[62], tabelaGraczyString[63] },
							  {2, tabelaGraczyString[65], tabelaGraczyString[66] },
							  {3, tabelaGraczyString[68], tabelaGraczyString[69] },
							  {4, tabelaGraczyString[71], tabelaGraczyString[72]},
							  {5, tabelaGraczyString[74], tabelaGraczyString[75]}, 
							  {6, tabelaGraczyString[77], tabelaGraczyString[78]},
							  {7, tabelaGraczyString[80], tabelaGraczyString[81]}, 
							  {8, tabelaGraczyString[83], tabelaGraczyString[84]},
							  {9, tabelaGraczyString[86], tabelaGraczyString[87]},
							  {10, tabelaGraczyString[89], tabelaGraczyString[90]}
							};
							table = new JTable(data, columnNames);
							table.setDefaultEditor(Object.class, null);
							table.setBounds(200, 130, 510, 410);
							table.setBorder(BorderFactory.createEmptyBorder());
							table.setBackground(new Color(224, 224, 224));
							return table;
						}
		
		return table;
	
	}

}
