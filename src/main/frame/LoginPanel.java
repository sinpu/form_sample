package main.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import window.MainWindow;

import learningModel.LearnerModel;

public class LoginPanel extends JPanel implements AbstractPanel,ActionListener{
	
	private JTextField idTextField;
	private JTextField passTextField;
	
	private MainWindow mainWindow;
	
	public LoginPanel(MainWindow mainFrame){
		super();
		
		mainWindow = mainFrame;
		makeView();
	}
	
	private void makeView(){

		this.setLayout(null);
		
		JLabel lblLoginSys = new JLabel("LOGIN SYSTEM");
		lblLoginSys.setFont(new Font("Dialog", Font.BOLD, 35));
		lblLoginSys.setBounds(281, 128, 436, 71);
		lblLoginSys.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblLoginSys);
		
		idTextField = new JTextField();
		idTextField.setBounds(530, 270, 114, 19);
		this.add(idTextField);
		idTextField.setColumns(10);
		
		passTextField = new JTextField();
		passTextField.setBounds(530, 360, 114, 19);
		this.add(passTextField);
		passTextField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(420, 270, 70, 15);
		this.add(lblId);
		
		JLabel lblPass = new JLabel("PASS");
		lblPass.setBounds(420, 360, 70, 15);
		this.add(lblPass);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(465, 430, 117, 25);
		this.add(btnLogin);
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = idTextField.getText();
		String pass = passTextField.getText();
		
		mainWindow.startSystem(id, pass);
	}

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		
	}

}
