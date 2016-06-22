package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.event.EventListenerList;

import org.eclipse.ui.internal.handlers.WizardHandler.New;

import main.frame.TransitionModel;

public class MenuListModel extends DefaultListModel<Object> implements ActionListener{

	// protected EventListenerList listenerList
	//private String[] list = {TransitionModel.Home.toString(),"PROPERTY","EXIT"};
	
	public MenuListModel(){
		super();
		init();
	}
	
	private void init(){
		
		for(TransitionModel data : TransitionModel.values() ){
			this.addElement(data.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	

}
