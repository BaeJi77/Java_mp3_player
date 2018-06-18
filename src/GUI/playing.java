package GUI;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class playing extends Thread {
	private File file;
	
	
	public playing(File file) {
		this.file = file;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = new FileInputStream(file);
			Player nowMp3 = new Player(fis);
			nowMp3.play();
		}catch(Exception Ea){
			
		}finally {
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
	}
}
