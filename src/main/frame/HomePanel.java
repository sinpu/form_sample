package main.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel implements ActionListener{

	private TransitionModel display;
	
	public HomePanel(){
		super();
		
		makeView();
	}
	
	public HomePanel(TransitionModel e){
		super();
		
		display = e;
		makeView();
	}
	
	private void makeView(){


		//empty part 1
		JTextField textField = new JTextField();
		this.add(textField);
		textField.setColumns(10);

		//main text
		JTextField txtHelloLearner = new JTextField();
		txtHelloLearner.setFont(new Font("Dialog", Font.BOLD, 23));
		txtHelloLearner.setHorizontalAlignment(SwingConstants.CENTER);
		txtHelloLearner.setText("Hello　Learner！");
		this.add(txtHelloLearner);
		txtHelloLearner.setColumns(10);
		
		//empty part 2
		JTextField textField_2 = new JTextField();
		this.add(textField_2);
		textField_2.setColumns(10);
		
		//empty part 3
		JTextField textField_1 = new JTextField();
		this.add(textField_1);
		textField_1.setColumns(10);
		
		//start button
		JButton btnNewButton = new JButton("Click Start!");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.addActionListener(this);
		this.add(btnNewButton);
		
		//empty part 4
		JTextField textField_3 = new JTextField();
		this.add(textField_3);
		textField_3.setColumns(10);


	}
	
	public void actionPerformed(ActionEvent e){
		display = TransitionModel.Learning;
	}
}
