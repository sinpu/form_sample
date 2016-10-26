package main.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import window.MainWindow;

import learningModel.LearnerModel;
import menu.TransitionModel;

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
		//idTextField.setText(mainWindow.getUser().getID());
		
		passTextField = new JPasswordField();
		passTextField.setBounds(530, 360, 114, 19);
		this.add(passTextField);
		passTextField.setColumns(10);
		passTextField.setText(mainWindow.getUser().getPASS());
		passTextField.setVisible(false);
		
		JLabel lblId = new JLabel("School ID");
		lblId.setBounds(400, 270, 150, 15);
		this.add(lblId);
		lblId.setFont(new Font("Arial BOLD ITALIC",Font.PLAIN,20));
		
		JLabel lblPass = new JLabel("PASS");
		lblPass.setBounds(420, 360, 70, 20);
		this.add(lblPass);
		lblPass.setVisible(false);
		
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
		
		if(null == id || "".equals(id) ){
			JOptionPane.showMessageDialog(mainWindow, "Please Input School ID !!");
		}
		
		if(checkID(id)){
			mainWindow.startSystem(id, pass);
		}else{
			JOptionPane.showMessageDialog(mainWindow, "Please Input Right School ID !!");
		}
	}
	
	private boolean checkID(String SID){		
		String id = SID.toLowerCase();
		
		char[] parts = id.toCharArray();
		
		if( parts.length != 7){
			return false;
		}
		
		if( parts[0] != 'a' && parts[0] != 'm'){
			return false;
		}
		
		if( parts[1] != 'l' && parts[1] != 'a'){
			return false;
		}

		if( parts[2] != '1' ){
			return false;
		}
		
		if( parts[3] != '5' && parts[3] != '6'){
			return false;
		}
		
		if( parts[4] != '0' && parts[4] != '0'){
			return false;
		}
		
		if( 47 < parts[5] && parts[5] < 58){
			return false;
		}
		
		if( 47 < parts[6] && parts[6] < 58){
			return false;
		}
		
		return true;
	}
	

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		
	}

}
