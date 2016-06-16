package menu;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.event.EventListenerList;

public class MenuListModel extends DefaultListModel<Object>{

	// protected EventListenerList listenerList
	private String[] list = {"HOME","PROPERTY","EXIT"};
	
	public MenuListModel(){
		super();
		init();
	}
	
	private void init(){
		for(String data : list ){
		this.addElement(data);
		}
	}
	
	

}
