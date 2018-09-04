package function;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javazoom.jl.player.Player;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.*;
import javax.swing.*;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.tools.JavaFileManager;

import javax.swing.JTextArea;

import function.player;
import function.BadButtonActionException;
import javax.swing.JScrollPane;

public class Main extends JFrame implements ActionListener {

	private JFrame frmMusic;
	private JPanel btnList;
	private JPanel musicList;
	private JPanel btnPane;
	private JPanel InfoDetail;
	private JLabel LBsongName;
	private JLabel LBartist;
	private JLabel LBsongTime;
	private JProgressBar GUIprogressBar;
	private JButton btnBack;
	private JButton btnPlay;
	private JButton btnNext;
	private JButton btnStop;
	private JLabel lblVolume;
	private JPanel musicInfo;
	private JButton btnAddsong;
	private JButton btnOpenFile;
	private JButton btnOpenlist;
	private JButton btnRemovesong;
	private JButton btnNewlist;
	private JButton btnRemovelist;
	private JButton btnSavelist;
	private JScrollPane scrollPane;
	private JToolBar toolBar;
	private JLabel lblName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JSlider volumeSlider;

	private JList<music> list;
	private DefaultListModel<music> listModel;
	private player Musicplayer;
	private Thread progressBarThread;
	private String playingTime;
	private int duration;
	private int nowAvailable;
	private int totalIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		Musicplayer = new player(null, 0, 0);
		duration = 0;
		progressBar();
		progressBarThread.stop();
		resetPlaying();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMusic = new JFrame();
		frmMusic.setTitle("Music Player");
		frmMusic.setBounds(100, 100, 450, 550);
		frmMusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusic.getContentPane().setLayout(new BorderLayout(0, 0));

		musicInfo = new JPanel();
		frmMusic.getContentPane().add(musicInfo, BorderLayout.NORTH);
		musicInfo.setLayout(new GridLayout(0, 1, 0, 0));

		InfoDetail = new JPanel();
		musicInfo.add(InfoDetail);
		GridBagLayout gbl_InfoDetail = new GridBagLayout();
		gbl_InfoDetail.columnWidths = new int[] { 0, 114, 145, 132, 0 };
		gbl_InfoDetail.rowHeights = new int[] { 20, 0, 0, 0 };
		gbl_InfoDetail.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_InfoDetail.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		InfoDetail.setLayout(gbl_InfoDetail);

		LBsongName = new JLabel("songName");
		GridBagConstraints gbc_LBsongName = new GridBagConstraints();
		gbc_LBsongName.gridwidth = 3;
		gbc_LBsongName.anchor = GridBagConstraints.WEST;
		gbc_LBsongName.insets = new Insets(0, 0, 5, 0);
		gbc_LBsongName.gridx = 1;
		gbc_LBsongName.gridy = 0;
		InfoDetail.add(LBsongName, gbc_LBsongName);

		LBartist = new JLabel("artist");
		GridBagConstraints gbc_LBartist = new GridBagConstraints();
		gbc_LBartist.gridwidth = 2;
		gbc_LBartist.anchor = GridBagConstraints.WEST;
		gbc_LBartist.insets = new Insets(0, 0, 5, 5);
		gbc_LBartist.gridx = 1;
		gbc_LBartist.gridy = 1;
		InfoDetail.add(LBartist, gbc_LBartist);

		LBsongTime = new JLabel("songTime");
		GridBagConstraints gbc_LBsongTime = new GridBagConstraints();
		gbc_LBsongTime.anchor = GridBagConstraints.WEST;
		gbc_LBsongTime.insets = new Insets(0, 0, 0, 5);
		gbc_LBsongTime.gridx = 1;
		gbc_LBsongTime.gridy = 2;
		InfoDetail.add(LBsongTime, gbc_LBsongTime);

		GUIprogressBar = new JProgressBar();
		GridBagConstraints gbc_GUIprogressBar = new GridBagConstraints();
		gbc_GUIprogressBar.gridwidth = 2;
		gbc_GUIprogressBar.gridx = 2;
		gbc_GUIprogressBar.gridy = 2;
		InfoDetail.add(GUIprogressBar, gbc_GUIprogressBar);

		btnPane = new JPanel();
		musicInfo.add(btnPane);
		btnPane.setLayout(new GridLayout(1, 1, 0, 0));
		ImageIcon BackIcon = new ImageIcon("./resources/previous.png");
		btnBack = new JButton(BackIcon);
		btnBack.setMnemonic(KeyEvent.VK_B); // This also works with the ALT+B shortcut.
		btnBack.addActionListener(this);
		btnPane.add(btnBack);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);

		ImageIcon PlayIcon = new ImageIcon("./resources/play.png");
		btnPlay = new JButton(PlayIcon);
		btnPlay.setMnemonic(KeyEvent.VK_P); // This also works with the ALT+P shortcut.
		btnPlay.addActionListener(this);
		btnPane.add(btnPlay);
		btnPlay.setBorderPainted(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setFocusPainted(false);

		ImageIcon StopIcon = new ImageIcon("./resources/stop.png");
		btnStop = new JButton(StopIcon);
		btnStop.setMnemonic(KeyEvent.VK_S); // This also works with the ALT+S shortcut.
		btnStop.addActionListener(this);
		btnPane.add(btnStop);
		btnStop.setBorderPainted(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setFocusPainted(false);

		ImageIcon NextIcon = new ImageIcon("./resources/next.png");
		btnNext = new JButton(NextIcon);
		btnNext.setMnemonic(KeyEvent.VK_N); // This also works with the ALT+N shortcut.
		btnNext.addActionListener(this);
		btnPane.add(btnNext);
		btnNext.setBorderPainted(false);
		btnNext.setContentAreaFilled(false);
		btnNext.setFocusPainted(false);

		ImageIcon VolumeIcon = new ImageIcon("./resources/volume.png");
		lblVolume = new JLabel(VolumeIcon);
		btnPane.add(lblVolume);

		btnList = new JPanel();
		frmMusic.getContentPane().add(btnList, BorderLayout.SOUTH);
		GridBagLayout gbl_btnList = new GridBagLayout();
		gbl_btnList.columnWidths = new int[] { 168, 123, 165, 0 };
		gbl_btnList.rowHeights = new int[] { 29, 29, 29, 29, 0 };
		gbl_btnList.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_btnList.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		btnList.setLayout(gbl_btnList);

		ImageIcon FopenIcon = new ImageIcon("./resources/opendoc.png");
		btnOpenFile = new JButton("Openfile", FopenIcon);
		btnOpenFile.addActionListener(this);
		GridBagConstraints gbc_btnOpenFile = new GridBagConstraints();
		gbc_btnOpenFile.fill = GridBagConstraints.BOTH;
		gbc_btnOpenFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenFile.gridx = 0;
		gbc_btnOpenFile.gridy = 0;
		btnOpenFile.setBorderPainted(false);
		btnOpenFile.setContentAreaFilled(false);
		btnOpenFile.setFocusPainted(false);
		btnList.add(btnOpenFile, gbc_btnOpenFile);

		ImageIcon FopenlistIcon = new ImageIcon("./resources/opendoc.png");
		btnOpenlist = new JButton("Open playlist", FopenlistIcon);
		btnOpenlist.addActionListener(this);
		GridBagConstraints gbc_btnOpenlist = new GridBagConstraints();
		gbc_btnOpenlist.fill = GridBagConstraints.BOTH;
		gbc_btnOpenlist.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenlist.gridx = 2;
		gbc_btnOpenlist.gridy = 0;
		btnOpenlist.setBorderPainted(false);
		btnOpenlist.setContentAreaFilled(false);
		btnOpenlist.setFocusPainted(false);
		btnList.add(btnOpenlist, gbc_btnOpenlist);

		ImageIcon AddSongIcon = new ImageIcon("./resources/add.png");
		btnAddsong = new JButton("Add song", AddSongIcon);
		btnAddsong.addActionListener(this);
		GridBagConstraints gbc_btnAddsong = new GridBagConstraints();
		gbc_btnAddsong.fill = GridBagConstraints.BOTH;
		gbc_btnAddsong.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddsong.gridx = 0;
		gbc_btnAddsong.gridy = 1;
		btnAddsong.setBorderPainted(false);
		btnAddsong.setContentAreaFilled(false);
		btnAddsong.setFocusPainted(false);
		btnList.add(btnAddsong, gbc_btnAddsong);

		ImageIcon NewPlayIcon = new ImageIcon("./resources/new_list.png");
		btnNewlist = new JButton("New playlist", NewPlayIcon);
		btnNewlist.addActionListener(this);
		GridBagConstraints gbc_btnNewlist = new GridBagConstraints();
		gbc_btnNewlist.fill = GridBagConstraints.BOTH;
		gbc_btnNewlist.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewlist.gridx = 2;
		gbc_btnNewlist.gridy = 1;
		btnNewlist.setBorderPainted(false);
		btnNewlist.setContentAreaFilled(false);
		btnNewlist.setFocusPainted(false);
		btnList.add(btnNewlist, gbc_btnNewlist);

		ImageIcon RemoveSongIcon = new ImageIcon("./resources/remove_song.png");
		btnRemovesong = new JButton("Remove song", RemoveSongIcon);
		btnRemovesong.addActionListener(this);
		GridBagConstraints gbc_btnRemovesong = new GridBagConstraints();
		gbc_btnRemovesong.fill = GridBagConstraints.BOTH;
		gbc_btnRemovesong.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemovesong.gridx = 0;
		gbc_btnRemovesong.gridy = 2;
		btnRemovesong.setBorderPainted(false);
		btnRemovesong.setContentAreaFilled(false);
		btnRemovesong.setFocusPainted(false);
		btnList.add(btnRemovesong, gbc_btnRemovesong);

		ImageIcon SaveIcon = new ImageIcon("./resources/save.png");
		btnSavelist = new JButton("Save playlist", SaveIcon);
		btnSavelist.addActionListener(this);
		GridBagConstraints gbc_btnSavelist = new GridBagConstraints();
		gbc_btnSavelist.fill = GridBagConstraints.BOTH;
		gbc_btnSavelist.insets = new Insets(0, 0, 5, 0);
		gbc_btnSavelist.gridx = 2;
		gbc_btnSavelist.gridy = 2;
		btnSavelist.setBorderPainted(false);
		btnSavelist.setContentAreaFilled(false);
		btnSavelist.setFocusPainted(false);
		btnList.add(btnSavelist, gbc_btnSavelist);

		ImageIcon RemoveListIcon = new ImageIcon("./resources/remove_list.png");
		btnRemovelist = new JButton("Remove playlist", RemoveListIcon);
		btnRemovelist.addActionListener(this);
		GridBagConstraints gbc_btnRemovelist = new GridBagConstraints();
		gbc_btnRemovelist.fill = GridBagConstraints.BOTH;
		gbc_btnRemovelist.gridx = 2;
		gbc_btnRemovelist.gridy = 3;
		btnRemovelist.setBorderPainted(false);
		btnRemovelist.setContentAreaFilled(false);
		btnRemovelist.setFocusPainted(false);
		btnList.add(btnRemovelist, gbc_btnRemovelist);

		musicList = new JPanel();
		frmMusic.getContentPane().add(musicList, BorderLayout.CENTER);
		musicList.setLayout(new GridLayout(0, 1, 0, 0));

		scrollPane = new JScrollPane();
		musicList.add(scrollPane);

		toolBar = new JToolBar();
		scrollPane.setColumnHeaderView(toolBar);

		listModel = new DefaultListModel<music>();
		list = new JList<music>(listModel);
		scrollPane.setViewportView(list);

		lblName = new JLabel("Song Name");
		toolBar.add(lblName);

		lblNewLabel = new JLabel("                       ");
		toolBar.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Artist");
		toolBar.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("                     ");
		toolBar.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Duration");
		toolBar.add(lblNewLabel_3);
	}

	// ProgressBar Thread
	public void progressBar() {
		//Basic setting from MusicPlayer Pane 
		duration = Musicplayer.getDuration();
		playingTime = String.format("%02d:%02d", duration / 60, duration % 60);
		GUIprogressBar.setValue(0);
		LBsongTime.setText(playingTime);
		int init = Musicplayer.getTotalSongLength();
		progressBarThread = new Thread() {
			public void run() {
				while (Musicplayer.getPaused() == false || Musicplayer.isCompleted() == false) {
					//continuous update MusicPlayer Pane
					nowAvailable = Musicplayer.getAvailable();
					double tempAvailable = init - nowAvailable;
					double updateValue = (tempAvailable / init) * 100;
					GUIprogressBar.setValue((int) updateValue);
					LBsongTime.setText(playingTime);
					try {
						//This thread sleep 1sec
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch(NullPointerException NPE) {
						System.err.println(NPE.getMessage());
					}	catch (Exception ex) {
						System.err.println(ex.getMessage());
					} finally {
						//to update duration
						duration--;
						playingTime = String.format("%02d:%02d", duration / 60, duration % 60);
					}
				}
			}
		};
		progressBarThread.start();
		
		/*
		 * To play the next song auto (but, make a error)
		 */
//		if(Musicplayer.isCompleted() == true && Musicplayer.getIndex() < totalIndex) {
//			MusicStart(Musicplayer.getIndex()+1);
//		}else {
//			resetPlaying();
//		}
	}

	// MusicStart Thread to play another song
	public void MusicStart(int inputIndex) {
		//to kill playing music now
		Musicplayer.MusicStop();
		progressBarThread.stop();
		String fileUrl = "";
		String MusicName = "";
		String artist = "";
		playingTime = "";
		try {
			//to select the index because I have another music information
			Scanner scan = new Scanner(new FileInputStream("playList.txt"));
			if (inputIndex == 0) {
				artist = scan.nextLine();
				MusicName = scan.nextLine();
				playingTime = scan.nextLine();
				fileUrl = scan.nextLine();
			} else {
				for (int i = 0; i < (inputIndex - 1) * 4; i++) {
					scan.nextLine();
				}
				artist = scan.nextLine();
				MusicName = scan.nextLine();
				playingTime = scan.nextLine();
				fileUrl = scan.nextLine();
			}
		} catch (FileNotFoundException FNF) {
			System.err.println(FNF.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		//Make new thread that play the music
		Musicplayer = new player(fileUrl, inputIndex, Integer.parseInt(playingTime));
		Musicplayer.setMusicName(MusicName);
		Musicplayer.setArtist(artist);
		//Update the Pane
		LBsongName.setText(Musicplayer.getMusicName());
		LBartist.setText(Musicplayer.getArtist());
		//And work the new ProgressBar and music player
		progressBar();
		Musicplayer.start();
	}
	
	//reset the MusicPlayer Pane
	public void resetPlaying() {
		LBsongName.setText("SongName");
		LBartist.setText("Artist");
		LBsongTime.setText("00:00");
		GUIprogressBar.setValue(0);
		totalIndex = 0;
	}
	
	//Remove the Music information
	//We cann't implement the several indices remove
	//This work just one index.
	public void removeSongBtn(int[] indices) {
		try {
			//We make tempfile. After working, tempfile is renamed the original file name.
			File inputFile = new File("playList.txt");
			File tempFile = new File("tempFile.txt");
			Scanner scan = new Scanner(new FileInputStream(inputFile));
			PrintWriter pw = new PrintWriter(new FileOutputStream(tempFile));
			for (int i = indices.length - 1; i >= 0; i--) {
				//if index is 0
				if (indices[i] == 0) {
					for (int z = 0; z < 4; z++) {
						scan.nextLine();
					}
					while (scan.hasNextLine()) {
						pw.println(scan.nextLine());
					}
					scan.close();
				} else {
					//if index is not 0
					for (int j = 0; j < (indices[i]) * 4; j++) {
						pw.println(scan.nextLine());
					}
					for (int z = 0; z < 4; z++) {
						scan.nextLine();
					}
					while (scan.hasNextLine()) {
						pw.println(scan.nextLine());
					}
					scan.close();
				}
			}
			scan.close();
			pw.close();
			boolean successful = tempFile.renameTo(inputFile);
		} catch (FileNotFoundException fne) {
			System.err.println(fne.getMessage());
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	// ActionListener
	public void actionPerformed(ActionEvent e) {
		// Handle open button action.
		if (e.getSource() == btnOpenFile) {
			// Implement btnOpenFile actionListener
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(Main.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				resetPlaying();
				File file = fc.getSelectedFile();
				listModel.clear();
				music newMusic = new music(file);
				newMusic.openFileBtn();
				listModel.addElement(newMusic);
				totalIndex++;
			}
		} else if (e.getSource() == btnAddsong) {
			// Implement btnAddsong actionListener
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(Main.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				music newMusic = new music(file);
				newMusic.addSongBtn();
				listModel.addElement(newMusic);
				totalIndex++;
			}
		} else if (e.getSource() == btnRemovesong) {
			// Implement btnRemoveSong actionListener
			int[] indices = list.getSelectedIndices();
			int returnValue = JOptionPane.showConfirmDialog(frmMusic, "Are you share you want to delete this contact",
					"Delete", JOptionPane.YES_NO_OPTION);
			if (returnValue == JOptionPane.YES_OPTION) {
				for (int i = indices.length - 1; i >= 0; i--) {
					listModel.removeElementAt(indices[i]);
					totalIndex--;
				}
				// TODO : update the playList
				removeSongBtn(indices);
			}
		} else if (e.getSource() == btnOpenlist) {
			// Implement btnOpenlist actionListener
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(Main.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				resetPlaying();
				listModel.clear();
				File file = fc.getSelectedFile();
				try {
					int firstCnt = 0;
					Scanner scan = new Scanner(new FileInputStream(file));
					while (scan.hasNextLine()) {
						String newArtist = scan.nextLine();
						String newMusicName = scan.nextLine();
						String newPlayingTime = scan.nextLine();
						String newFileUrl = scan.nextLine();
						File newFile = new File(newFileUrl);
						music newMusic = new music(newFile);
						if (firstCnt == 0)
							newMusic.openFileBtn();
						else
							newMusic.addSongBtn();
						firstCnt++;
						listModel.addElement(newMusic);
						totalIndex++;
					}
					list.validate();
					scan.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception ex) {
					System.err.println(ex);
				}
			}
		} else if (e.getSource() == btnNewlist) {
			// Implement btnNewlist actionListener
			int returnValue = JOptionPane.showConfirmDialog(frmMusic, "Are you share you want to New PlayList ?", "Yes",
					JOptionPane.YES_NO_OPTION);
			if (returnValue == JOptionPane.YES_OPTION) {
				resetPlaying();
				listModel.clear();
				try {
					PrintWriter pw = new PrintWriter(new FileOutputStream("playList.txt"));
					pw.print("");
					pw.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == btnSavelist) {
			// Implement btnSavelist actionListener
			JFileChooser fc = new JFileChooser();
			int ret = fc.showSaveDialog(frmMusic);
			if (ret == JFileChooser.APPROVE_OPTION) {
				FileInputStream fis;
				FileOutputStream fos;
				try {
					fis = new FileInputStream("playList.txt"); // Original file
					fos = new FileOutputStream(fc.getSelectedFile()); // copy file
					int data = 0;
					while ((data = fis.read()) != -1) {
						fos.write(data);
					}
					fis.close();
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {

				}
			}
		} else if (e.getSource() == btnRemovelist) {
			// Implement btnRemovelist actionListener
			int returnValue = JOptionPane.showConfirmDialog(frmMusic, "Are you share you want to delete PlayList ?",
					"delete", JOptionPane.YES_NO_OPTION);
			if (returnValue == JOptionPane.YES_OPTION) {
				resetPlaying();
				listModel.clear();
				try {
					PrintWriter pw = new PrintWriter(new FileOutputStream("playList.txt"));
					pw.print("");
					pw.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == btnPlay) {
			// Implement btnPlay actionListener
			int index = list.getSelectedIndex() + 1;
			System.out.println("getSelectedIndex : " + index);
			// Check the same song or new song that is playing now.
			if (index == Musicplayer.getIndex() && Musicplayer.getPaused()) {
				// if same music
				System.out.println("같은 노래입니다!!!!");
				LBsongName.setText(Musicplayer.getMusicName());
				LBartist.setText(Musicplayer.getArtist());
				Musicplayer.MusicResume();
				progressBar();
			} else {
				// if new music
				MusicStart(index);
			}
		} else if (e.getSource() == btnStop) {
			// Implement btnStop actionListener
			// This work the pause button
			Musicplayer.pause(duration);
		} else if (e.getSource() == btnNext) {
			// Implement btnNext actionListener
			int nextIndex = Musicplayer.getIndex() + 1;
			try {
				if (nextIndex > totalIndex) {
					//if nextIndex is wrong action, we catch the exception to make the exception class
					BadButtonActionException BBAE = new BadButtonActionException();
					JOptionPane.showMessageDialog(null, BBAE.getMessage());
				}
				boolean badIndex = false;
				MusicStart(nextIndex);
				System.out.println("nextIndex : " + nextIndex);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}

		} else if (e.getSource() == btnBack) {
			// Implement btnBack actionListener
			try {
				if (Musicplayer.getIndex() == 1) {
					//if backIndex is wrong action, we catch the exception to make the exception class
					BadButtonActionException BBAE = new BadButtonActionException();
					JOptionPane.showMessageDialog(null, BBAE.getMessage());
				}
				int backIndex = Musicplayer.getIndex() - 1;
				MusicStart(backIndex);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
