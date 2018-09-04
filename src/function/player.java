package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.FieldPosition;
import javazoom.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class player extends Thread {
	private String musicName;
	private String artist;
	private int index;
	private String musicFilePath;
	private int available;
	private boolean completed;
	private int duration;
	private int pauseDuration;

	private boolean paused;
	private int pauseLocation;
	private int totalSongLength;
	private FileInputStream fis;
	private Player player;
	
	
	// Implement the thread that play the music
	public player(String musicFilePath, int index , int duration) {
		this.index = index;
		this.musicFilePath = musicFilePath;
		this.duration = duration;
		try {
			fis = new FileInputStream(musicFilePath);
			totalSongLength = fis.available();
			player = new Player(fis);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}catch (IOException ex) {
			System.err.println(ex.getMessage());
		} catch(Exception exx) {
			System.err.println(exx.getMessage());
		}
		new Thread() {
			@Override
			public void run() {
				if(musicFilePath != null) {
					try {
						player.play();
					} catch (Exception exx) {
						System.err.println("::: there was an error to play " + musicFilePath);
					}
				}
			}
		}.start();
	}

	// Implement the thread that play the music
	// After pausing the music, This method run in place
	public void MusicResume() {
		paused = false;
		try {
			fis = new FileInputStream(musicFilePath);
			fis.skip(totalSongLength - pauseLocation);
			duration = pauseDuration;
			player = new Player(fis);
		}catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}catch (IOException ex) {
			System.err.println(ex.getMessage());
		} catch(Exception exx) {
			System.err.println(exx.getMessage());
		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException ex) {
					System.err.println("::: there was an error to play " + musicFilePath);
				} catch(Exception exx) {
					
				}
			}
		}.start();
	}
	
	// This method kill thread working now.
	public void MusicStop() {
		paused = false;

		if (null != player) {
			player.close();
			totalSongLength = 0;
			pauseLocation = 0;
		}
	}
	
	// Pause
	public void pause(int nowDuration) {
		paused = true;
		if (null != player) {
			try {
				pauseLocation = fis.available();
				pauseDuration = nowDuration;
				player.close();
			} catch (IOException ex) {
				System.out.println("::: error when song is paused");
			}
		}
	}
	
	public int getTotalSongLength() {
		return totalSongLength;
	}

	public void setTotalSongLength(int totalSongLength) {
		this.totalSongLength = totalSongLength;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAvailable() {
		try {
			available = fis.available();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public boolean isCompleted() {
		completed = player.isComplete();
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean getPaused() {
		return paused;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
}
