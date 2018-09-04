package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Scanner;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.*;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

public class music {
	private String name;
	private String artist;
	private File file;
	private int duration; 
	
	//Make the music file information
	public music(File file) {
		String[] strSplit = file.getName().split("-");
		this.name = strSplit[0].trim();
		this.artist = strSplit[1].trim();
		this.file = file;
		try {
			AudioFile audioFile = AudioFileIO.read(file);
			this.duration = audioFile.getAudioHeader().getTrackLength();
		}catch(Exception ex) {
			System.err.println(ex);
		}
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	//toString format
	public String toString() {
		return String.format("%20s%20s%20s%02d:%02d", name, artist,"",duration/60 , duration%60);
	}
	
	//addSongBtn implement
	public void addSongBtn() {
		try{
			//Allow the append 
			PrintWriter fw = new PrintWriter(new FileOutputStream("playList.txt",true));
			fw.println(name);
			fw.println(artist);
			fw.println(duration);
			fw.println(file);
			
			fw.close();
		}catch(FileNotFoundException fne) {
			System.out.println(fne.getMessage());
		}
	}
	
	//addSongBtn implement
	public void openFileBtn() {
		
		try {
			//Now allow the append
			PrintWriter fw = new PrintWriter(new FileOutputStream("playList.txt"));
			fw.println(name);
			fw.println(artist);
			fw.println(duration);
			fw.println(file);
			fw.close();
		}catch(FileNotFoundException fne){
			System.err.println(fne.getMessage());
		}
	}

}
