package main.frame;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class LearningPanel extends JPanel implements AbstractPanel{
	
	private JFrame mainWindowPanelFrame;
	
	public LearningPanel(){
		super();
		
		makeView();
	}

	public LearningPanel(JFrame mainFrame){
		super();
		
		mainWindowPanelFrame = mainFrame;
		makeView();
	}
	
	private void makeView(){
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(splitPane_1);		
	
		JLabel lblNewLabel = new JLabel(new ImageIcon("/home/sinpu/ダウンロード/kadai02.png"),JLabel.CENTER);
		splitPane_1.setLeftComponent(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(2, 4, 0, 0));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Answer00");
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Answer01");
		panel_2.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Answer02");
		panel_2.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Answer03");
		panel_2.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Answer04");
		panel_2.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Answer05");
		panel_2.add(rdbtnNewRadioButton_5);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("Answer06");
		panel_2.add(rdbtnNewRadioButton_6);
		
		JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("Answer07");
		panel_2.add(rdbtnNewRadioButton_7);
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		
		JButton btnNewButton = new JButton("Back");
		panel_3.add(btnNewButton);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton btnNewButton1 = new JButton("Home");
		panel_3.add(btnNewButton1);
		btnNewButton1.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton1.setVerticalAlignment(SwingConstants.BOTTOM);
				
		JButton btnNewButton2 = new JButton("Answer");
		panel_3.add(btnNewButton2);
		btnNewButton2.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton2.setVerticalAlignment(SwingConstants.BOTTOM);
		
		
	}

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
	}
}
