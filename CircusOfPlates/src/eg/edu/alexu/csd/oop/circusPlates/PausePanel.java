package eg.edu.alexu.csd.oop.circusPlates;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.objects.BackgroundObject;
import eg.edu.alexu.csd.oop.worlds.clownWorld;

public class PausePanel extends JPanel{

private static final long serialVersionUID = 1L;
	
	private AudioFactory audios;
	
	private Image mainImage, resumeImage, menuImage, replayImage;
	
	public JButton resumeBtn;
	public JButton replayBtn;
	
	private World currentWorld;

	public PausePanel(World world) {
		this.currentWorld = world;
		audios = AudioFactory.getInstance();
		
		setBounds(150, 120, 300, 300);
		setBackground(new Color(0,0,0,200));
		setLayout(null);
		
		resumeBtn = new JButton("");
		resumeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < currentWorld.getComponents().size() - 1; i++) {
					currentWorld.getComponents().get(i).setEnabled(true);
				}
				CircusFacade.getController().resume();
				setVisible(false);
			}
		});
		resumeBtn.setBounds(10, 235, 54, 54);
		
		
		JButton menuBtn = new JButton("");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audios.stop();
				CircusFacade.getController().changeWorld(null);
				new UserInterface();
			}
		});
		menuBtn.setBounds(236, 235, 54, 54);
		
		replayBtn = new JButton("");
		replayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < currentWorld.getComponents().size() - 1; i++) {
					currentWorld.getComponents().get(i).setEnabled(true);
				}
				setVisible(false);
				CircusFacade.getController().resume();
				URL path = ((BackgroundObject)currentWorld.getConstantObjects().get(0)).getPath();
				CircusFacade.getController().changeWorld(new clownWorld(1280, 687, path));
			}
		});
		replayBtn.setBounds(123, 235, 54, 54);
		
		try {
			mainImage = ImageIO.read(PausePanel.class.getResource("/res/Images/creepyClown.jpg"));
			resumeImage = ImageIO.read(PausePanel.class.getResource("/res/Images/Play_BTN.png"));
			menuImage = ImageIO.read(PausePanel.class.getResource("/res/Images/Menu_BTN.png"));
			replayImage = ImageIO.read(PausePanel.class.getResource("/res/Images/Replay_BTN.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resumeBtn.setIcon(new ImageIcon(resumeImage.getScaledInstance(resumeBtn.getWidth(), resumeBtn.getHeight(), Image.SCALE_SMOOTH)));
		menuBtn.setIcon(new ImageIcon(menuImage.getScaledInstance(menuBtn.getWidth(), menuBtn.getHeight(), Image.SCALE_SMOOTH)));
		replayBtn.setIcon(new ImageIcon(replayImage.getScaledInstance(replayBtn.getWidth(), replayBtn.getHeight(), Image.SCALE_SMOOTH)));
		
		add(resumeBtn);
		add(menuBtn);
		add(replayBtn);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		g.drawImage(mainImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
	}
	
}
