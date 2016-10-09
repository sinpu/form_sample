package main.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
	
	//button
	private JButton nextBtn;
	private JButton checkBtn;
	private JButton backBtn;
	
	
	public LearningPanel(){
		super();
		
		makeView();
	}

	public LearningPanel(MainWindow mainFrame){
		super();
		
		mainWindow = mainFrame;
		start();
		makeView();
	}
	
	private void start(){
		if(null == problemData){
			problemData = new ProblemModel();		
		}else{
			problemData.saveLearningData();
			problemData = new ProblemModel();
		}
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
		
		backBtn = new JButton(BACK_BUTTON_STRING);
		panel_3.add(backBtn);
		backBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		backBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		backBtn.addActionListener(this);
		backBtn.setVisible(false);
		
		checkBtn = new JButton(CHECK_BUTTON_STRING);
		panel_3.add(checkBtn);
		checkBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		checkBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		checkBtn.addActionListener(this);
				
		nextBtn = new JButton(NEXT_BUTTON_STRING);
		panel_3.add(nextBtn);
		nextBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		nextBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		nextBtn.addActionListener(this);
		nextBtn.setVisible(false);
		
	}
	
	/* Button Setting */
	private void btnSetting(JPanel panel){
		for(int i = 0 ; i < ANSWER_SIZE ; i++){
			rbAnswerButtons[i] = new JRadioButton("Answer0" + i);
			panel.add(rbAnswerButtons[i]);
			
			rbAnswerButtons[i].setFont(new Font("Arial BOLD ITALIC",Font.PLAIN,20));
			
		}
	}
	
	/* set Answer */
	private void setAnswer(){
		ArrayList<String> answerList = problemData.getAnswer();
		
		for(int i = 0 ; i < ANSWER_SIZE ; i++){
			rbAnswerButtons[i].setText("");
			rbAnswerButtons[i].setVisible(true);
			rbAnswerButtons[i].setSelected(false);
			rbAnswerButtons[i].setBackground(null);
		}

		Random rand = new Random();
		int tmp = 0;
		int t = 0;
		for(int i = ANSWER_SIZE ; i > 0 ; i--){
			tmp = rand.nextInt(i);
			t = i - 1;
			rbAnswerButtons[t].setText(answerList.get(tmp));
			answerList.remove(tmp);
			if(rbAnswerButtons[t].getText().equals("-1")){
				rbAnswerButtons[t].setVisible(false);
			}
		}
	}
	
	/* set Image of Problem */
	private void setProblemImage(){
		subjectLabel.setIcon(new ImageIcon(problemData.getProblem()));
	}

	@Override
	public void changePanel(TransitionModel e) {
		// TODO Auto-generated method stub
		mainWindow.changePanel(e);
	}

	/* Push Answer Button */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		if(command.equals(BACK_BUTTON_STRING)){
			problemData.previosProblem();
			
		}else if(command.equals(CHECK_BUTTON_STRING)){
			checkAnswer();
			
		}else if(command.equals(NEXT_BUTTON_STRING)){
			nextBtn.setVisible(false);
			checkBtn.setEnabled(true);
			/* finish movement */
			if(!problemData.nextProblem()) {
				changePanel(TransitionModel.Home);
				return ;
			}
			setProblemImage();
			setAnswer();
		}		
		
	}
	
	/* Grading Answer */
	private void checkAnswer(){
		
		for(int i = 0 ; i < rbAnswerButtons.length ; i++){
			if(rbAnswerButtons[i].isSelected()){
				if( problemData.checkProblemAnswer(rbAnswerButtons[i].getText())){
					rbAnswerButtons[i].setBackground(Color.BLUE);
				}else{
					rbAnswerButtons[i].setBackground(Color.RED);
				}
			}			
		}
		
		problemData.setSubProblem();
		showBestAnswer();
		nextBtn.setVisible(true);
		checkBtn.setEnabled(false);
	}
	
	/*  */
	private void showBestAnswer(){
		ArrayList<String> tmpList = problemData.getKyojiURIlist();
		if(tmpList.isEmpty()) return;
		
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
