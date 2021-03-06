package main.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.crypto.Data;

import org.eclipse.ui.internal.handlers.NewEditorHandler;

import learningModel.AnswerModel;
import learningModel.ProblemModel;
import menu.TransitionModel;

import window.MainWindow;

public class LearningPanel extends JPanel implements AbstractPanel,ActionListener,MouseListener{
	
	//main window
	private MainWindow mainWindow;
	
	//answer view
	private JFrame answerWindow;
	private JPanel answerImagePanel;
	private JPanel thumbnailPanel;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JViewport viewport;
	private JScrollBar scrollBar;
	
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
	
	//private ImageIcon right = new ImageIcon("src/learning_system/image/right_answer.png");
	//private ImageIcon wrong = new ImageIcon("src/learning_system/image/wrong_answer.png");
	private ImageIcon right = new ImageIcon( getClass().getClassLoader().getResource("right_answer.png"));
	private ImageIcon wrong = new ImageIcon( getClass().getClassLoader().getResource("wrong_answer.png"));
		
	
	private TransitionModel studyType;
	private int kyojiCard = 0;
	
	public String problemSize = "";
	
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
	
	public void start(String startProblem){
		problemData.resetCheckList();
		problemData.startStudy( startProblem );
	
		setProblemImage();
		setAnswer();
		updateProblemSize();
	}
	
	/* learning Display */
	private void makeView(){
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(splitPane_1);		
	
		subjectLabel = new JLabel(new ImageIcon( getClass().getClassLoader().getResource(problemData.getProblem())),JLabel.CENTER);
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
			rbAnswerButtons[i].setIcon(null);
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
		subjectLabel.setIcon(new ImageIcon( getClass().getClassLoader().getResource(problemData.getProblem())));
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
			if(!problemData.nextProblem( studyType )) {
				changePanel(TransitionModel.Home);
				updateProblemSize();
				return ;
			}
			setProblemImage();
			setAnswer();
			updateProblemSize();
		}		
		
	}
	
	/* Grading Answer */
	private void checkAnswer(){
		ArrayList<String> ansList = new ArrayList<String>();
		int k = 0;
		
		for(int i = 0 ; i < rbAnswerButtons.length ; i++){
			if(rbAnswerButtons[i].isSelected()){
				k++;
				String answer = rbAnswerButtons[i].getText();
				ansList.add( actAnswer(answer) );
				if( problemData.checkProblemAnswer( answer )){
					rbAnswerButtons[i].setBackground(Color.CYAN);
					rbAnswerButtons[i].setIcon(right);
				}else{
					rbAnswerButtons[i].setBackground(Color.ORANGE);
					rbAnswerButtons[i].setIcon(wrong);
				}
			}else {
				String answer = rbAnswerButtons[i].getText();
				problemData.setKyojiRightAnswer(answer);
			}
		}
		
		if( k == 0 ){
			problemData.setWrongFlag();
			ansList.add("NoAnswer");
		}
		
		mainWindow.user.setRecord(problemData.getProblem(), ansList);
		problemData.setSubProblem();
		showBestAnswer();
		nextBtn.setVisible(true);
		checkBtn.setEnabled(false);
	}
	
	private String actAnswer(String answer){
		StringBuffer newAnswer = new StringBuffer();
		
		for(char tmp : answer.toCharArray()){
			if( tmp == ' ') continue;
			
			newAnswer.append(tmp);
		}
		
		return newAnswer.toString();
	}
	
	/*  */
	private void showBestAnswer(){
		ArrayList<String> tmpList = problemData.getKyojiURIlist();
		
		if(tmpList.isEmpty()) return;
		
		
		if(null != answerWindow ){
			answerWindow.setVisible(false);
		}
		
		answerWindow = new JFrame();
		Point point = mainWindow.getLocationOnScreen();
		answerWindow.setBounds(point.x + 900, point.y, 800, 450);
		answerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		answerWindow.getContentPane().setLayout(new BoxLayout(answerWindow.getContentPane(), BoxLayout.X_AXIS));
			
		splitPane = new JSplitPane();
		
		thumbnailPanel = new JPanel();
		thumbnailPanel.setLayout(new BoxLayout(thumbnailPanel, BoxLayout.Y_AXIS));
		
		answerImagePanel = new JPanel();
		answerImagePanel.setLayout(new BoxLayout(answerImagePanel, BoxLayout.Y_AXIS));
		//answerWindow.add(answerImagePanel);
		
		scrollPane = new JScrollPane(answerImagePanel);
//				viewport = scrollPane.getViewport();
		scrollBar = scrollPane.getVerticalScrollBar();
			
		answerWindow.getContentPane().add(splitPane);
		splitPane.setRightComponent(scrollPane);
		splitPane.setLeftComponent(thumbnailPanel);
			
		int i = 0;
		for(String kyojiURI : tmpList){
			ImageIcon icon = new ImageIcon( getClass().getClassLoader().getResource(kyojiURI) );
			JLabel lbl = new JLabel( icon );
			lbl.setAlignmentY(1f);
			lbl.setBorder(new LineBorder(Color.BLUE));
			answerImagePanel.add( lbl );
			//System.out.println(icon.getIconHeight());
				
			ImageIcon thumbnail = new ImageIcon();
			Image mini = icon.getImage().getScaledInstance((int)(icon.getIconWidth() * 0.3), (int)(icon.getIconHeight() * 0.3), Image.SCALE_SMOOTH);
			thumbnail.setImage( mini );
			JLabel imageLbl = new JLabel( thumbnail );
			imageLbl.setName( kyojiURI );
			imageLbl.setBorder( new LineBorder(Color.BLACK)); 
			imageLbl.addMouseListener(this);
			thumbnailPanel.add( imageLbl );
			i++;
		}
			
		//余白作るためだけという
		JLabel tmp = new JLabel();
		tmp.setSize(new Dimension(answerImagePanel.getWidth(), 150));
		answerImagePanel.add(tmp);
			
		answerWindow.setVisible(true);
		
	}
	
	public void setStudyType(TransitionModel t){
		studyType = t;
	}
	
	public TransitionModel getStudyType(){
		return studyType;
	}
	
	public void updateProblemSize(){
		StringBuffer problemSize = new StringBuffer();
		problemSize.append("Left:");
		problemSize.append(problemData.getProblemLength( studyType ));

		mainWindow.updateSize(problemSize.toString());
	}
	
	private int searchImageIndex( String filename ){
		int i = 0;
		
		for(String imageName : problemData.getKyojiURIlist() ){
			if( imageName.equals( filename ) ) return i;
			i++;
		}
		
		return 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel tmp = (JLabel)e.getSource();
		int index = searchImageIndex( tmp.getName() );
		
		scrollBar.setValue(328 * index);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
