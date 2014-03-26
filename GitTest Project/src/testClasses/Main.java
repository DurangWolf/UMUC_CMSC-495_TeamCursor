//This is just a test program to verify that GIT can be used as intended.

package testClasses;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		
		//Components
		JFrame window;
		GridLayout layout;
		JLabel message;
		JButton button;
		
		//Create a message
		message = new JLabel("Welcome, you have loaded a project from GIT!");
		
		//Create a button
		button = new JButton("SWEET!");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}});
		
		
		//Create a frame
		layout = new GridLayout(2,0);
		window = new JFrame("GIT TEST");
		window.setLayout(layout);
		window.setSize(500, 100);
		window.setLocation(500, 500);
		
		//Add components to frame
		window.add(message);
		window.add(button);
		
		
		
		
		
		window.setVisible(true);
	}

}
