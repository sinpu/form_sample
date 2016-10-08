package main.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import menu.TransitionModel;

import window.*;

public class HomePanel extends JPanel implements ActionListener,AbstractPanel{

	private MainWindow mainWindowPanelFrame;
	
	public HomePanel(){
		super();
		
		makeView();
	}
	
	public HomePanel(MainWindow mainFrame){
		super();
		
		mainWindowPanelFrame = mainFrame;
		makeView();
	}
	
	private void makeView(){


		//empty part 1
		JTextField textField = new JTextField();
		this.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		

		//main text
		JTextField txtHelloLearner = new JTextField();
		this.add(txtHelloLearner);
		txtHelloLearner.setFont(new Font("Dialog", Font.BOLD, 23));
		txtHelloLearner.setHorizontalAlignment(SwingConstants.CENTER);
		txtHelloLearner.setText("Hello　Learner！");
		txtHelloLearner.setColumns(10);
		txtHelloLearner.setEditable(false);
		txtHelloLearner.setBorder(null);
		txtHelloLearner.setBackground(Color.WHITE);
		
		//empty part 2
		JTextField textField_2 = new JTextField();
		this.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		textField_2.setBorder(null);
		textField_2.setBackground(Color.WHITE);
		
		//empty part 3
		JTextField textField_1 = new JTextField();
		this.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.WHITE);
		
		//start button
		JButton btnNewButton = new JButton("Click Start!");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.addActionListener(this);
		this.add(btnNewButton);
		
		//empty part 4
		JTextField textField_3 = new JTextField();
		this.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		textField_3.setBorder(null);
		textField_3.setBackground(Color.WHITE);



	}
	
	public void actionPerformed(ActionEvent e){
		changePanel(TransitionModel.Learning);
	}

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		mainWindowPanelFrame.changePanel(e);
	}
}
