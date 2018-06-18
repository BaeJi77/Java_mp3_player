package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JTextArea;

public class Main {

	private JFrame frmMusic;
	private JPanel btnList;
	private JPanel songList;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel songName;
	private JLabel artist;
	private JLabel songTime;
	private JProgressBar progressBar;
	private JButton btnBack;
	private JButton btnPlay;
	private JButton btnNext;
	private JButton btnStop;
	private JLabel lblVolume;
	private JPanel songInfo;
	private JButton btnNewButton_1;
	private JButton btnOpenFile;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JToolBar toolBar;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMusic = new JFrame();
		frmMusic.setTitle("Music Player");
		frmMusic.setBounds(100, 100, 450, 390);
		frmMusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusic.getContentPane().setLayout(new BorderLayout(0, 0));
		
		songInfo = new JPanel();
		frmMusic.getContentPane().add(songInfo, BorderLayout.NORTH);
		songInfo.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_4 = new JPanel();
		songInfo.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 114, 145, 132, 0};
		gbl_panel_4.rowHeights = new int[]{20, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		songName = new JLabel("songName");
		GridBagConstraints gbc_songName = new GridBagConstraints();
		gbc_songName.gridwidth = 3;
		gbc_songName.anchor = GridBagConstraints.WEST;
		gbc_songName.insets = new Insets(0, 0, 5, 0);
		gbc_songName.gridx = 1;
		gbc_songName.gridy = 0;
		panel_4.add(songName, gbc_songName);
		
		artist = new JLabel("artist");
		GridBagConstraints gbc_artist = new GridBagConstraints();
		gbc_artist.gridwidth = 2;
		gbc_artist.anchor = GridBagConstraints.WEST;
		gbc_artist.insets = new Insets(0, 0, 5, 5);
		gbc_artist.gridx = 1;
		gbc_artist.gridy = 1;
		panel_4.add(artist, gbc_artist);
		
		songTime = new JLabel("songTime");
		GridBagConstraints gbc_songTime = new GridBagConstraints();
		gbc_songTime.anchor = GridBagConstraints.WEST;
		gbc_songTime.insets = new Insets(0, 0, 0, 5);
		gbc_songTime.gridx = 1;
		gbc_songTime.gridy = 2;
		panel_4.add(songTime, gbc_songTime);
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.gridx = 2;
		gbc_progressBar.gridy = 2;
		panel_4.add(progressBar, gbc_progressBar);
		
		panel_3 = new JPanel();
		songInfo.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 1, 0, 0));
		
		btnBack = new JButton("back");
		panel_3.add(btnBack);
		
		btnPlay = new JButton("play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_3.add(btnPlay);
		
		btnStop = new JButton("stop");
		panel_3.add(btnStop);
		
		btnNext = new JButton("next");
		panel_3.add(btnNext);
		
		lblVolume = new JLabel("volume");
		panel_3.add(lblVolume);
		
		btnList = new JPanel();
		frmMusic.getContentPane().add(btnList, BorderLayout.SOUTH);
		GridBagLayout gbl_btnList = new GridBagLayout();
		gbl_btnList.columnWidths = new int[]{168, 123, 165, 0};
		gbl_btnList.rowHeights = new int[]{29, 29, 29, 29, 0};
		gbl_btnList.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_btnList.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		btnList.setLayout(gbl_btnList);
		
		btnOpenFile = new JButton("Openfile");
		GridBagConstraints gbc_btnOpenFile = new GridBagConstraints();
		gbc_btnOpenFile.fill = GridBagConstraints.BOTH;
		gbc_btnOpenFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenFile.gridx = 0;
		gbc_btnOpenFile.gridy = 0;
		btnList.add(btnOpenFile, gbc_btnOpenFile);
		
		btnNewButton_3 = new JButton("Open playlist");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 0;
		btnList.add(btnNewButton_3, gbc_btnNewButton_3);
		
		btnNewButton_1 = new JButton("Add song");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		btnList.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_5 = new JButton("New playlist");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 2;
		gbc_btnNewButton_5.gridy = 1;
		btnList.add(btnNewButton_5, gbc_btnNewButton_5);
		
		btnNewButton_4 = new JButton("Remove song");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 2;
		btnList.add(btnNewButton_4, gbc_btnNewButton_4);
		
		btnNewButton_7 = new JButton("Save playlist");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 2;
		gbc_btnNewButton_7.gridy = 2;
		btnList.add(btnNewButton_7, gbc_btnNewButton_7);
		
		btnNewButton_6 = new JButton("Remove playlist");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_6.gridx = 2;
		gbc_btnNewButton_6.gridy = 3;
		btnList.add(btnNewButton_6, gbc_btnNewButton_6);
		
		songList = new JPanel();
		frmMusic.getContentPane().add(songList, BorderLayout.CENTER);
		songList.setLayout(new GridLayout(0, 1, 0, 0));
		
		toolBar = new JToolBar();
		songList.add(toolBar);
		
		lblNewLabel = new JLabel("New label");
		toolBar.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		toolBar.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("New label");
		toolBar.add(lblNewLabel_2);
		
		textArea = new JTextArea();
		songList.add(textArea);
	}

}
