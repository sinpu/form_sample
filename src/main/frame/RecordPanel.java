package main.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import window.MainWindow;

import menu.TransitionModel;

public class RecordPanel extends JPanel implements ActionListener,AbstractPanel{

	private MainWindow mainWindow;
	
	public RecordPanel(MainWindow mainFrame){
		super();
		
		mainWindow = mainFrame;
	}
	
	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
