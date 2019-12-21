package eg.edu.alexu.csd.oop.circusPlates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UserInterface extends JPanel{

private static final long serialVersionUID = 1L;
	
	private JFrame frmCircusPlates;
	private JLabel infoLbl;
	private JPanel circusPanel;
	
	private CircusFacade cf;
	
	private Image mainImage, start, exit, infoImage, night, fire, ice;
	
	private AudioFactory audio;

	private CircusLogger cl ;
	
	public UserInterface() {
		cf = new CircusFacade();
		audio = AudioFactory.getInstance();
		cl = new CircusLogger();
		try {
			
			mainImage = ImageIO.read(UserInterface.class.getResource("/res/Images/creepyClown.jpg"));
			start = ImageIO.read(UserInterface.class.getResource("/res/Images/Start_BTN.png"));
			exit = ImageIO.read(UserInterface.class.getResource("/res/Images/Exit_BTN.png"));
			infoImage = ImageIO.read(UserInterface.class.getResource("/res/Images/Info_BTN.png"));
			night = ImageIO.read(UserInterface.class.getResource("/res/Images/nightCircusImg.png"));
			fire = ImageIO.read(UserInterface.class.getResource("/res/Images/fireCircusImg.png"));
			ice = ImageIO.read(UserInterface.class.getResource("/res/Images/iceCircusImg.png"));

		} catch (IOException e) {
//			e.printStackTrace();
		}
		initialize();
		cl.InfoLog("User Interface initialized");
		frmCircusPlates.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCircusPlates = new JFrame();
		frmCircusPlates.setTitle("Circus Plates");
		frmCircusPlates.setBounds(50, 0, 1280, 720);
		frmCircusPlates.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCircusPlates.setIconImage(mainImage);
		frmCircusPlates.getContentPane().setLayout(null);
		this.setBounds(0, 0, frmCircusPlates.getWidth(), frmCircusPlates.getHeight());
		frmCircusPlates.getContentPane().add(this);
		setLayout(null);
		
		Image startScaled = null, exitScaled, infoScaled = null,
				nightScaled = null, fireScaled = null, iceScaled = null;
		
		JButton startButton = new JButton("");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(infoLbl.isVisible()) {
					infoLbl.setVisible(false);
				}
				circusPanel.setVisible(true);
			}
		});
		startButton.setBounds(10, 562, 124, 37);
		
		JButton exitButton = new JButton("");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.WarningLog("Game Ended");
				System.exit(0);
			}
		});
		exitButton.setBounds(10, 610, 124, 37);
		
		JButton infoBtn = new JButton("");
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(infoLbl.isVisible()) {
					infoLbl.setVisible(false);
				}else {
					infoLbl.setVisible(true);
				}
			}
		});
		infoBtn.setBounds(1184, 11, 48, 48);
		
		JButton nightBtn = new JButton("");
		nightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audio.stop();
				cf.chooseCircus(nightBtn);
				frmCircusPlates.dispose();
			}
		});
		nightBtn.setName("night");
		nightBtn.setBounds(17, 77, 79, 101);
		
		JButton fireBtn = new JButton("");
		fireBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audio.stop();
				cf.chooseCircus(fireBtn);
				frmCircusPlates.dispose();
			}
		});
		fireBtn.setName("fire");
		fireBtn.setBounds(115, 77, 79, 101);
		
		JButton iceBtn = new JButton("");
		iceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audio.stop();
				cf.chooseCircus(iceBtn);
				frmCircusPlates.dispose();
			}
		});
		iceBtn.setName("ice");
		iceBtn.setBounds(210, 77, 79, 101);
		
		startScaled = start.getScaledInstance(startButton.getWidth(), startButton.getHeight(), Image.SCALE_SMOOTH);
		exitScaled = exit.getScaledInstance(exitButton.getWidth(), exitButton.getHeight(), Image.SCALE_SMOOTH);
		infoScaled = infoImage.getScaledInstance(infoBtn.getWidth(), infoBtn.getHeight(), Image.SCALE_SMOOTH);
		nightScaled = night.getScaledInstance(nightBtn.getWidth(), nightBtn.getHeight(), Image.SCALE_SMOOTH);
		fireScaled = fire.getScaledInstance(fireBtn.getWidth(), fireBtn.getHeight(), Image.SCALE_SMOOTH);
		iceScaled = ice.getScaledInstance(iceBtn.getWidth(), iceBtn.getHeight(), Image.SCALE_SMOOTH);
		
		startButton.setIcon(new ImageIcon(startScaled));
		exitButton.setIcon(new ImageIcon(exitScaled));
		infoBtn.setIcon(new ImageIcon(infoScaled));
		nightBtn.setIcon(new ImageIcon(nightScaled));
		fireBtn.setIcon(new ImageIcon(fireScaled));
		iceBtn.setIcon(new ImageIcon(iceScaled));
		
		add(startButton);
		add(exitButton);
		add(infoBtn);
		
		infoLbl = new JLabel("<html>Try as hard as you can to group shapes with same color on the sticks</html>");
		infoLbl.setBounds(1018, 11, 156, 73);
		infoLbl.setVisible(false);
		add(infoLbl);
		infoLbl.setForeground(Color.WHITE);
		
		circusPanel = new JPanel();
		circusPanel.setBackground(new Color(0,0,0,160));
		circusPanel.setBounds(169, 457, 304, 190);
		circusPanel.setVisible(false);
		add(circusPanel);
		circusPanel.setLayout(null);
		
		JLabel lblPickACircus = new JLabel("Pick a Circus");
		lblPickACircus.setForeground(Color.WHITE);
		lblPickACircus.setFont(new Font("Rage Italic", Font.PLAIN, 28));
		lblPickACircus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickACircus.setBounds(0, 0, 304, 40);
		circusPanel.add(lblPickACircus);
		
		circusPanel.add(nightBtn);
		circusPanel.add(fireBtn);
		circusPanel.add(iceBtn);
		
		try {
			audio.play("intro", true);
		} catch (IOException e1) {
			cl.SevereLog("intro audio file not working ",e1);
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mainImage.getScaledInstance(frmCircusPlates.getWidth(), frmCircusPlates.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
	}
	
}
