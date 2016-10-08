package main.frame;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;

import learningModel.AnswerModel;
import learningModel.ProblemModel;
import menu.TransitionModel;

import window.MainWindow;

public class LearningPanel extends JPanel implements AbstractPanel,ActionListener{
	
	//main window
	private MainWindow mainWindow;
	
	//answer view
	private JFrame answerWindow;
	private JPanel answerImagePanel;
	
	//problem model
	private ProblemModel problemData;
	private static final int ANSWER_SIZE = 8;	
	private JLabel subjectLabel;
	private JRadioButton[] rbAnswerButtons = new JRadioButton[ANSWER_SIZE];
	
	//button id
	private String BACK_BUTTON_STRING = "Back";
	private String NEXT_BUTTON_STRING = "Next";
	private String CHECK_BUTTON_STRING = "Answer";
	
	
	public LearningPanel(){
		super();
		
		makeView();
	}

	public LearningPanel(MainWindow mainFrame){
		super();
		
		mainWindow = mainFrame;
		problemData = new ProblemModel();
		makeView();
		
	}
	
	/* learning Display */
	private void makeView(){
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(splitPane_1);		
	
		subjectLabel = new JLabel(new ImageIcon(problemData.getProblem()),JLabel.CENTER);
		splitPane_1.setLeftComponent(subjectLabel);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(2, 4, 0, 0));
				
		
		//set answer
		btnSetting(panel_2);
		setAnswer();
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		
		JButton backBtn = new JButton(BACK_BUTTON_STRING);
		panel_3.add(backBtn);
		backBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		backBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		backBtn.addActionListener(this);
		backBtn.setVisible(false);
		
		JButton homeBtn = new JButton(CHECK_BUTTON_STRING);
		panel_3.add(homeBtn);
		homeBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		homeBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		homeBtn.addActionListener(this);
				
		JButton answerBtn = new JButton(NEXT_BUTTON_STRING);
		panel_3.add(answerBtn);
		answerBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		answerBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		answerBtn.addActionListener(this);
		answerBtn.setVisible(false);
		
	}
	
	private void btnSetting(JPanel panel){
		for(int i = 0 ; i < ANSWER_SIZE ; i++){
			rbAnswerButtons[i] = new JRadioButton("Answer0" + i);
			panel.add(rbAnswerButtons[i]);
			
			rbAnswerButtons[i].setFont(new Font("Arial BOLD ITALIC",Font.PLAIN,20));
			
		}
	}
	
	private void setAnswer(){
		ArrayList<String> answerList = problemData.getAnswer();
		
		int i = 0;
		for(String answer : answerList){
			rbAnswerButtons[i].setText(answer);
			rbAnswerButtons[i].setSelected(false);
			i++;
		}
	}
	
	private void setProblemImage(){
		subjectLabel.setIcon(new ImageIcon(problemData.getProblem()));
	}

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		mainWindow.changePanel(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		if(command.equals(BACK_BUTTON_STRING)){
			problemData.previosProblem();
			
		}else if(command.equals(CHECK_BUTTON_STRING)){
			checkAnswer();
			
		}else if(command.equals(NEXT_BUTTON_STRING)){
			problemData.nextProblem();
		}		
		
		setProblemImage();
		setAnswer();
	}
	
	private void checkAnswer(){
		char[] answer = new char[8];
		
		for(int i = 0 ; i < rbAnswerButtons.length ; i++){
			if(rbAnswerButtons[i].isSelected()) answer[i] = '1';
			else answer[i] = '0'; 
		}
		
		if( problemData.checkAnswer(answer) ) showBestAnswer() ;
	}
	
	private void showBestAnswer(){
		if(null == answerWindow || !answerWindow.isShowing()){
			answerWindow = new JFrame();
			answerWindow.setBounds(1000, 100, 900, 600);
			answerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			answerWindow.getContentPane().setLayout(new BoxLayout(answerWindow.getContentPane(), BoxLayout.X_AXIS));
			
			answerImagePanel = new JPanel();
			answerImagePanel.setLayout(new BoxLayout(answerImagePanel, BoxLayout.Y_AXIS));
			//answerWindow.add(answerImagePanel);

			JScrollPane scrollPane = new JScrollPane(answerImagePanel);
			answerWindow.add(scrollPane);
			
			
			for(String kyojiURI : problemData.getKyojiURIlist()){

				//some answer need some JLavel
				answerImagePanel.add( new JLabel(new ImageIcon( kyojiURI ) ));

			}
			
			answerWindow.setVisible(true);
		}else{
			//for(String uri : model[]) answerImagePanel.add...
		}
		
	}
}
