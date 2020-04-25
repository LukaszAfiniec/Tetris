package com.tetris.implementation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileManager {
	private File appConfigFile;
	private File userHighScore;
	
	public FileManager() {
		appConfigFile = new File("config.txt");	     
		userHighScore = new File("userhighscore.txt");

		 try {
		     
		      
		      if (appConfigFile.createNewFile()) {
		        System.out.println("File config.txt created: " + appConfigFile.getName());
		      } else if (userHighScore.createNewFile()) {
			        System.out.println("File userhighscore.txt created: " + userHighScore.getName());
			      } 
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	
	}
	

	
	public void writeAppConfigFileMethod(StringBuilder sb) {
		 try {
		      FileWriter myWriter = new FileWriter("config.txt");
		      myWriter.write(sb.toString());
		      myWriter.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    } 
	}
	
	
	public void writeUserHighScoreFileMethod(StringBuilder sb) {
		 try {
		      FileWriter myWriter = new FileWriter("userhighscore.txt");
		      myWriter.write(sb.toString());
		      myWriter.close();		     
		    } catch (IOException e) {
		      e.printStackTrace();
		    } 
	}
	
	
	public String loadAppConfigFileMethod() {
		
			 StringBuilder sb = new StringBuilder();
			    try (Stream<String> stream = Files.lines(Paths.get(System.getProperty("user.dir")+"/config.txt") , StandardCharsets.UTF_8)){
			        stream.forEach(s -> sb.append(s).append("\n"));
			    }
			    catch (IOException e) {
			        e.printStackTrace();
			    } return sb.toString();
	}
	
}
