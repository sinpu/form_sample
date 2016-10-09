package window;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Paint;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.AbstractListModel;

import learningModel.LearnerModel;
import main.frame.HomePanel;
import main.frame.LearningPanel;
import main.frame.LoginPanel;
import menu.MenuListModel;
import menu.TransitionModel;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingConstants;


public class MainWindow extends JFrame{

	private JFrame frame;
		
	//main frame parts
	private JPanel home;
	private JPanel learning;
	private JPanel login;
	
	private JSplitPane splitPane;

	private LearnerModel learner;
	
	private JTextField idTextField;
	private JTextField passTextField;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = this;
	

		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		//login 
		login = new LoginPanel(this);
		frame.getContentPane().add(login);
		
		//menu & mainFrame
		splitPane = new JSplitPane();
		
		//main frame view
		/* home */
		home = new HomePanel(this);
		splitPane.setRightComponent(home);		
		home.setLayout(new GridLayout(2, 3, 0, 0));

		/* learning */
		learning = new LearningPanel(this);
		learning.setLayout(new BoxLayout(learning, BoxLayout.Y_AXIS));
		
		/* record */
		
		//menu view
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		splitPane.setLeftComponent(panel_1);
		
		MenuListModel menu = new MenuListModel(this);
		JButton[] menuBtn = new JButton[TransitionModel.values().length];
		int i = 0;
		for(TransitionModel data : TransitionModel.values()){
			menuBtn[i] = new JButton(data.toString()); 
			menuBtn[i].setMaximumSize(new Dimension(Short.MAX_VALUE, menuBtn[i].getMaximumSize().height));
			menuBtn[i].addActionListener(menu);
			panel_1.add(menuBtn[i]);
			i++;
		}
		
		/* 使って見たが、listenerが理想的ではない
		JList list = new JList();
		list.setModel(new MenuListModel());
		panel_1.add(list);
		*/
	}
	
	/* 使ってないね */
	public void startSystem(String id,String pass){
		learner = new LearnerModel(id, pass);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(splitPane);
		frame.getContentPane().revalidate();
	}
	
	//change display panel
	public void changePanel(TransitionModel display){
		
		switch (display) {
		case Home:
			splitPane.setRightComponent(home);
			break;

		case Learning:
			learning = new LearningPanel(this);
			learning.setLayout(new BoxLayout(learning, BoxLayout.Y_AXIS));
			splitPane.setRightComponent(learning);
			break;
			
		default:
			break;
		}
		
	}
	
}
