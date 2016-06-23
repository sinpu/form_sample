package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.event.EventListenerList;

import org.eclipse.ui.internal.handlers.WizardHandler.New;

import window.MainWindow;

import main.frame.TransitionModel;

public class MenuListModel extends DefaultListModel<Object> implements ActionListener{

	// protected EventListenerList listenerList
	//private String[] list = {TransitionModel.Home.toString(),"PROPERTY","EXIT"};
	
	private MainWindow mainWindow;
	
	public MenuListModel(){
		super();
		init();
	}
	
	public MenuListModel(MainWindow mainFrame){
		super();
		
		mainWindow = mainFrame;
	}
	
	public void init(){
		for(TransitionModel data : TransitionModel.values() ){
			addElement(data.toString());
		}		
	}
	
	
	public JButton[] init2(){
		JButton[] jButtons = new JButton[TransitionModel.values().length];
		
		int i = 0;
		for(TransitionModel data : TransitionModel.values() ){
			jButtons[i] = new JButton(data.toString());
		}
		
		return jButtons;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		mainWindow.changePanel(TransitionModel.valueOf(command));
	}
	
	

}
