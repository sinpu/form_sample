package learningModel;

import java.awt.Checkbox;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.eclipse.core.internal.runtime.Log;
import org.eclipse.jface.text.templates.Template;

import com.ibm.icu.text.UTF16;

public class ProblemModel {

	//select answer
	//private Map<String, Integer> answerList = new Map<String, Integer>();
	private ArrayList<String> answerList = new ArrayList<String>();
	
	private ArrayList<String> problemsList = new ArrayList<String>();
	
	//problem
	private String problemImageURI = new String();
	private String answerTextURI = new String();
	
	//answer
	private String[] answerImageURI = new String[8];

	private String PROBLEM_LIST_FILE = "/home/sinpu/learning_system/problem_list.txt";
	private int PROBLEM_FILE_INDEX = 0;
	private int ANSWER_FILE_INDEX = 1;
	
	private int indexOfProblem;
	
	public ProblemModel(){
		String tmpString = "";
		
		indexOfProblem = 0;
		
		try{
			FileReader fReader = new FileReader(new File(PROBLEM_LIST_FILE));
			BufferedReader bReader = new BufferedReader(fReader);
			
			while( (tmpString = bReader.readLine()) != null){
				problemsList.add(tmpString);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		
		//debug
		//checkString(problemsList.toArray());
		setProblemModel();
	}
	
	public void setAnswer(){
		answerList.clear();
		
		try{
			FileReader fReader = new FileReader(new File(answerTextURI));
			BufferedReader bReader = new BufferedReader(fReader);
			
			String tmpString = "";
			while( (tmpString = bReader.readLine()) != null){
				answerList.add(tmpString);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//debug
		//checkString(answerList.toArray());
	}
	
	public String getProblem(){
		return problemImageURI;
	}
	
	public ArrayList<String> getAnswer(){
		return answerList;
	}
	
	public void nextProblem(){
		indexOfProblem++;
		if( (problemsList.size() - 1 )< indexOfProblem) indexOfProblem = 0;
		setProblemModel();
	}
	
	public void previosProblem(){
		indexOfProblem--;
		
		if(indexOfProblem < 0) indexOfProblem = problemsList.size() - 1;
		
		setProblemModel();
	}
	
	private void setProblemModel(){
		ArrayList<String> tmpList = new ArrayList<String>();
		String tmpString = "";
		
		try{
			FileReader fileReader = new FileReader(new File(problemsList.get(indexOfProblem)));
			BufferedReader bReader = new BufferedReader(fileReader);
			
			while( (tmpString = bReader.readLine()) != null){
				tmpList.add(tmpString);
			}
			
			problemImageURI = tmpList.get(PROBLEM_FILE_INDEX);
			answerTextURI = tmpList.get(ANSWER_FILE_INDEX);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//debug
		//checkString(tmpList.toArray());
		setAnswer();
	}
	
	private void checkString(Object[] tmp){
		JFrame answerWindow = new JFrame();
		answerWindow.setBounds(1000, 100, 300, 600);
		answerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		answerWindow.getContentPane().setLayout(new BoxLayout(answerWindow.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel answerImagePanel = new JPanel();
		answerImagePanel.setLayout(new BoxLayout(answerImagePanel, BoxLayout.Y_AXIS));
		answerWindow.add(answerImagePanel);
		
		//some answer need some JLavel
		JTextArea textArea = new JTextArea();
		answerImagePanel.add(textArea);
		
		StringBuffer tString = new StringBuffer();
		for(Object data : tmp){
			tString.append(data.toString());
			tString.append("\n");
		}
		textArea.setText(tString.toString());
		
		answerWindow.setVisible(true);

	}
}
