package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.event.EventListenerList;

import main.frame.TransitionModel;

public class MenuListModel extends DefaultListModel<Object> implements ActionListener{

	// protected EventListenerList listenerList
	private String[] list = {TransitionModel.Home.toString(),"PROPERTY","EXIT"};
	
	public MenuListModel(){
		super();
		init();
	}
	
	private void init(){
		for(String data : list ){
		this.addElement(data);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
