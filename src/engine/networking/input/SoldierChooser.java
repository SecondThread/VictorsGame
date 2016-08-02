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
import engine.game.Window;
import engine.networking.Client;
import engine.networking.ClientMain;

public class SoldierChooser {
	private static JFrame frame;
	private static JPanel mainPanel;
	private static JPanel playerTypePanel, ipPanel, funModePanel;
	private static JButton chooseSniper, chooseTank, chooseRunner;
	private static JLabel ipLabel, funModeLabel, superBoringModeLabel, smallWindowLabel;
	private static JTextField ipField;
	private static JCheckBox funModeCheckbox, superBoringModeCheckbox, smallWindowCheckbox;
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
		mainPanel.add(ipPanel);
		mainPanel.add(funModePanel);
		mainPanel.add(playerTypePanel);
		mainPanel.setPreferredSize(new Dimension(550,130));
		
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
		
		ipField=new JTextField(Client.serverIP);
		ipPanel.add(ipField);
	}
	
	private static void setUpFunModePanel() {
		funModePanel=new JPanel();
		
		funModeLabel=new JLabel("SUPER HAPPY FUNTIME!!");
		funModePanel.add(funModeLabel);
		
		funModeCheckbox=new JCheckBox("", true);
		funModePanel.add(funModeCheckbox);
		
		superBoringModeLabel=new JLabel("         super boring mode:");
		funModePanel.add(superBoringModeLabel);
		
		superBoringModeCheckbox=new JCheckBox("", false);
		funModePanel.add(superBoringModeCheckbox);
		
		smallWindowLabel=new JLabel("         Economy-sized Window:");
		funModePanel.add(smallWindowLabel);
		
		smallWindowCheckbox=new JCheckBox("", false);
		funModePanel.add(smallWindowCheckbox);
	}
	
	public static void setChoice(int choice) {
		SoldierChooser.choice=choice;
		Client.serverIP=ipField.getText();
		Window.fancyMode=!superBoringModeCheckbox.isSelected();
		Window.scaleFactor=smallWindowCheckbox.isSelected()?2:1;
		ClientMain.drawParticles=funModeCheckbox.isSelected();
	}

}