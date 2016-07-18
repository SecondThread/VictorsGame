package engine.networking.input;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.game.Main;

public class SoldierChooser {
	private static JFrame frame;
	private static JPanel mainPanel;
	private static JButton chooseSniper, chooseTank, chooseRunner;
	private static volatile int choice=-1;
	public static int chooseType() {
		createJFrame();
		while(choice==-1) {
			Main.wait(0.01);
		}
		frame.dispose();
		return choice;
	}
	
	private static void createJFrame() {
		chooseSniper=new JButton("Sniper");
		chooseTank=new JButton("Tank");
		chooseRunner=new JButton("Runner");
		
		chooseSniper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				SoldierChooser.setChoice(0);
			}
		});
		chooseTank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				SoldierChooser.setChoice(1);
			}
		});
		chooseRunner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				SoldierChooser.setChoice(2);
			}
		});
		
		mainPanel=new JPanel();
		mainPanel.add(chooseSniper);
		mainPanel.add(chooseTank);
		mainPanel.add(chooseRunner);
		mainPanel.setPreferredSize(new Dimension(400, 40));
		
		frame=new JFrame("Choose your Soldier");
		frame.add(mainPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void setChoice(int choice) {
		SoldierChooser.choice=choice;
	}
}