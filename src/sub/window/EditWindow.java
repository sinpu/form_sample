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


public class EditWindow {

	private JFrame frame;

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
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		//menu & mainFrame
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane);
		
		//main frame view
		JPanel panel = new LearningPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		//menu view
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		
		JList list = new JList();
		list.setModel(new MenuListModel());
		panel_1.add(list);
		
	}
}
