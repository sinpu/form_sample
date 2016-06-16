package main.frame;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel{

	public HomePanel(){
		super();
	}
	
	private void makeView(){
		JTextField txtHelloLearner = new JTextField();
		txtHelloLearner.setFont(new Font("Dialog", Font.BOLD, 23));
		txtHelloLearner.setHorizontalAlignment(SwingConstants.CENTER);
		txtHelloLearner.setText("Hello　Learner！");
		this.add(txtHelloLearner);
		txtHelloLearner.setColumns(10);
		
		JButton btnNewButton = new JButton("Click Start!");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		this.add(btnNewButton);

	}
	
}
