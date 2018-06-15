import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class Main {

	private JFrame frmMusic;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

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
		frmMusic.setBounds(100, 100, 450, 300);
		frmMusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frmMusic.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);
		
		panel_1 = new JPanel();
		frmMusic.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		panel_2 = new JPanel();
		frmMusic.getContentPane().add(panel_2, BorderLayout.CENTER);
	}

}
