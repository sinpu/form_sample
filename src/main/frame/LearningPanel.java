package main.frame;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import window.MainWindow;

public class LearningPanel extends JPanel implements AbstractPanel,ActionListener{
	
	//main window
	private MainWindow mainWindowPanelFrame;
	
	//answer view
	private JFrame answerWindow;
	private JPanel answerImagePanel;
	
	private JLabel subjectLabel;
	private JRadioButton[] rbAnswerButtons = new JRadioButton[8];
	
	
	public LearningPanel(){
		super();
		
		makeView();
	}

	public LearningPanel(MainWindow mainFrame){
		super();
		
		mainWindowPanelFrame = mainFrame;
		makeView();
	}
	
	private void makeView(){
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(splitPane_1);		
	
		subjectLabel = new JLabel(new ImageIcon("/home/sinpu/ダウンロード/kadai02.png"),JLabel.CENTER);
		splitPane_1.setLeftComponent(subjectLabel);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(2, 4, 0, 0));
		
		rbAnswerButtons[0] = new JRadioButton("Answer00");
		panel_2.add(rbAnswerButtons[0]);
		
		rbAnswerButtons[1]= new JRadioButton("Answer01");
		panel_2.add(rbAnswerButtons[1]);
		
		rbAnswerButtons[2] = new JRadioButton("Answer02");
		panel_2.add(rbAnswerButtons[2]);

		rbAnswerButtons[3] = new JRadioButton("Answer03");
		panel_2.add(rbAnswerButtons[3]);
		
		rbAnswerButtons[4] = new JRadioButton("Answer04");
		panel_2.add(rbAnswerButtons[4]);
		
		rbAnswerButtons[5] = new JRadioButton("Answer05");
		panel_2.add(rbAnswerButtons[5]);
		
		rbAnswerButtons[6] = new JRadioButton("Answer06");
		panel_2.add(rbAnswerButtons[6]);
		
		rbAnswerButtons[7] = new JRadioButton("Answer07");
		panel_2.add(rbAnswerButtons[7]);
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		
		JButton backBtn = new JButton("Back");
		panel_3.add(backBtn);
		backBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		backBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton homeBtn = new JButton("AnswerCheck");
		panel_3.add(homeBtn);
		homeBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		homeBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Answer check & best Answer show
				if(null == answerWindow){
					answerWindow = new JFrame();
					answerWindow.setBounds(1000, 100, 300, 600);
					answerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					answerWindow.getContentPane().setLayout(new BoxLayout(answerWindow.getContentPane(), BoxLayout.X_AXIS));
					
					answerImagePanel = new JPanel();
					answerImagePanel.setLayout(new BoxLayout(answerImagePanel, BoxLayout.Y_AXIS));
					answerWindow.add(answerImagePanel);
					
					//some answer need some JLavel
					JLabel answerImage = new JLabel(new ImageIcon("/home/sinpu/ダウンロード/kadai02.png"));
					answerImagePanel.add(answerImage);

					JLabel answerImage2 = new JLabel(new ImageIcon("/home/sinpu/ダウンロード/kadai02.png"));
					answerImagePanel.add(answerImage2);

					
					answerWindow.setVisible(true);
				}else{
					//for(String uri : model[]) answerImagePanel.add...
				}
			}
		});
				
		JButton answerBtn = new JButton("Answer");
		panel_3.add(answerBtn);
		answerBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		answerBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		answerBtn.addActionListener(this);
		
	}

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		mainWindowPanelFrame.changePanel(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}
}
