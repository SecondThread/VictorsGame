package engine.networking.input;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.game.Main;

public class SoldierChooser {
	private static JFrame frame;
	private static JPanel mainPanel;
	private static JPanel playerTypePanel, ipPanel, funModePanel;
	private static JButton chooseSniper, chooseTank, chooseRunner;
	private static JLabel ipLabel, funModeLabel, superBoringModeLabel;
	private static JTextField ipField;
	private static JCheckBox funModeCheckBox, superBoringModeCheckbox;
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
		setUpPlayerTypePanel();
		setUpIPPanel();
		setUpFunModePanel();

		mainPanel=new JPanel();
		mainPanel.add(playerTypePanel);
		mainPanel.add(ipPanel);
		mainPanel.add(funModePanel);
		mainPanel.setPreferredSize(new Dimension(400,130));
		
		frame=new JFrame("Choose your Soldier");
		frame.add(mainPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private static void setUpPlayerTypePanel() {
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
		
		playerTypePanel=new JPanel();
		playerTypePanel.add(chooseSniper);
		playerTypePanel.add(chooseTank);
		playerTypePanel.add(chooseRunner);
		playerTypePanel.setPreferredSize(new Dimension(400, 40));
	}
	
	private static void setUpIPPanel() {
		ipPanel=new JPanel();
		ipLabel=new JLabel("Server IP: ");
		ipPanel.add(ipLabel);
		
		ipField=new JTextField();
		ipPanel.add(ipField);
	}
	
	private static void setUpFunModePanel() {
		funModePanel=new JPanel();
		
		funModeLabel=new JLabel("SUPER HAPPY FUNTIME!!");
		funModePanel.add(funModeLabel);
		
		funModeCheckBox=new JCheckBox();
		funModePanel.add(funModeCheckBox);
		
		superBoringModeLabel=new JLabel("         super boring mode:");
		funModePanel.add(superBoringModeLabel);
		
		superBoringModeCheckbox=new JCheckBox();
		funModePanel.add(superBoringModeCheckbox);
	}
	
	public static void setChoice(int choice) {
		SoldierChooser.choice=choice;
	}
}