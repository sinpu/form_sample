package sub.window;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.AbstractListModel;

import main.frame.LearningPanel;
import menu.MenuListModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import menu.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class EditWindow {

	private JFrame frame;
	private JTextField txtHelloLearner;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditWindow window = new EditWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	

	/**
	 * Create the application.
	 */
	public EditWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	

		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginSys = new JLabel("LOGIN SYSTEM");
		lblLoginSys.setFont(new Font("Dialog", Font.BOLD, 35));
		lblLoginSys.setBounds(281, 128, 436, 71);
		lblLoginSys.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLoginSys);
		
		textField_4 = new JTextField();
		textField_4.setBounds(530, 270, 114, 19);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(530, 360, 114, 19);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(420, 270, 70, 15);
		panel.add(lblId);
		
		JLabel lblPass = new JLabel("PASS");
		lblPass.setBounds(420, 360, 70, 15);
		panel.add(lblPass);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(465, 430, 117, 25);
		panel.add(btnLogin);
		
	
		
		
	}
}
