package main.frame;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class LearningPanel extends JPanel implements AbstractPanel{
	
	public LearningPanel(){
		super();
		
		makeView();
	}

	private void makeView(){
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(splitPane_1);		
	
		JLabel lblNewLabel = new JLabel("Image Test",new ImageIcon("/home/sinpu/ダウンロード/kadai02.png"),JLabel.CENTER);
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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		this.add(btnNewButton);

	}

	@Override
	public void changePanel(Enum e) {
		// TODO Auto-generated method stub
		
	}
}
