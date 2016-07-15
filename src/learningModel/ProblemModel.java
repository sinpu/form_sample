package learningModel;

import java.awt.Checkbox;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.eclipse.core.internal.runtime.Log;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.ui.internal.handlers.WizardHandler.New;

import com.ibm.icu.text.UTF16;

public class ProblemModel {

	//select answer
	//private Map<String, Integer> answerList = new Map<String, Integer>();
	private ArrayList<AnswerModel> answerList = new ArrayList<AnswerModel>();
	private ArrayList<String> problemsList = new ArrayList<String>();
	private ArrayList<String> subProblemList = new ArrayList<String>();
	private ArrayList<String> kyojiList = new ArrayList<String>();
	private ArrayList<String> checkSubProblemList = new ArrayList<String>();
	private ArrayList<String> giveKyojiList = new ArrayList<String>();
	
	//problem
	private String problemImageURI = new String();
	private String answerTextURI = new String();
	private String kyojiImageURI = new String();
	private String focusProblem;
	
	//answer
	private String[] answerImageURI = new String[8];

	private String SYSTEM_PASS = "/home/sinpu/learning_system/";
	private String PROBLEM_LIST_FILE = "problem.csv";
	private String SUB_PROBLEM_LIST_FILE = "subkyoji.csv";
	private String MAIN = "problem";
	private String SUB = "sub";
	private String KYOJI = "kyoji";
	
	private int PROBLEM_FILE_INDEX = 0;
	private int ANSWER_FILE_INDEX = 1;
	
	private int indexOfProblem;
	private int indexOfSubProblem = 99;
	
	
	private int SUB_PROBLEM_MAX = 24;
	
	
	public ProblemModel(){
		String tmpString = "";
		String listString = "";
		indexOfProblem = 0;
		
		// set problem list
		try{
			FileReader fReader = new FileReader(new File(SYSTEM_PASS + PROBLEM_LIST_FILE));
			BufferedReader bReader = new BufferedReader(fReader);

			while( (tmpString = bReader.readLine()) != null){
				listString = tmpString;
			}

			String[] problem = listString.split(",");
			
			
			for(String tmp : problem){
				problemsList.add(tmp);
			}
			focusProblem = problemsList.get(PROBLEM_FILE_INDEX);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// set sub problem list
		try{
			FileReader fileReader = new FileReader(new File(SYSTEM_PASS + SUB_PROBLEM_LIST_FILE));
			BufferedReader bReader = new BufferedReader(fileReader);
			String[] dataString = new String[5];
			int i = 0;
			
			while( (tmpString = bReader.readLine()) != null){
				dataString[i] = tmpString;
				i++;
			}
			
			String[] data = dataString[3].split(",");
			for(String tmp : data){
				subProblemList.add(tmp);
			}
			
			data = dataString[4].split(",");
			for(String tmp : data){
				kyojiList.add(tmp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/* debug */
		//checkString(kyojiList.toArray());
		setProblem( MAIN );
	}
	
	public String getProblem(){
		return problemImageURI;
	}

	public ArrayList<String> getKyojiURIlist(){
		return giveKyojiList;
	}

	
	public ArrayList<String> getAnswer(){
		ArrayList<String> arrayList = new ArrayList<String>();
		for(AnswerModel data : answerList){
			arrayList.add(data.getAnswer());
		}
		return arrayList;
	}
	
	public void nextProblem(){
		String problemType = "";
		
		if( checkSubProblemList.isEmpty()){
			indexOfProblem++;
			focusProblem = problemsList.get(indexOfProblem);
			problemType = MAIN;
		}else{
			focusProblem = checkSubProblemList.get(indexOfSubProblem);
			problemType = SUB;
		}
		
		/* debug */
		checkString(giveKyojiList.toArray());
		
		setProblem(problemType);
	}
	
	public void previosProblem(){
		//indexOfProblem--;
		
		if(indexOfProblem < 0) indexOfProblem = problemsList.size() - 1;
		
		//setProblemModel();
	}
	
	private void checkProblemAnswer(char[] answer){
		int i = 0, j = 0;
		char[][] subCheckList = new char[answer.length][SUB_PROBLEM_MAX];

		
		for(i = 0; i < answer.length ; i++){
			for(j = 0 ; j < SUB_PROBLEM_MAX ; j++){
				subCheckList[i][j] = '0'; 
			}
		}
		
		i = 0;
		for(AnswerModel answerModel : answerList){
			int ans = Character.getNumericValue(answerModel.getCheckAnswer()) + Character.getNumericValue(answer[i]);
			
			switch(ans){
				case 0:
					break;
					
				case 1:
					j = 0;
					for(char value : answerModel.getSubList()){
						subCheckList[i][j] = value;
						j++;
					}
					break;
					
				case 2:
					break;
					
				default:
					break;
			}
			i++;
		}
		
		for(i = 1 ; i < answer.length ; i++){
			for(j = 0 ; j < SUB_PROBLEM_MAX ; j++){
				subCheckList[0][j] += Character.getNumericValue(subCheckList[i][j]);
			}
		}
		
		
		for(i = 0 ; i < SUB_PROBLEM_MAX ; i++){
			if( Character.getNumericValue(subCheckList[0][i]) > 0) checkSubProblemList.add(subProblemList.get(i));
		}

		indexOfSubProblem = checkSubProblemList.size() -1;
	}
	
	public void checkSubAnswer(char[] answer){
		int i = 0;
		boolean killFlag = true;
		
		for(AnswerModel answerModel : answerList){
			int ans = Character.getNumericValue(answerModel.getCheckAnswer()) + Character.getNumericValue(answer[i]);
			
			switch(ans){
				case 0:
					break;
					
				case 1:
					giveKyojiList.add(kyojiImageURI);
					killFlag = false;
					break;
					
				case 2:
					break;
					
				default:
					break;
			}
			i++;
		}
		
		if(!killFlag) checkSubProblemList.remove(indexOfSubProblem);
			
		indexOfSubProblem--;
	}
	
	public boolean checkAnswer(char[] answer){
		boolean kyojiFlag = false;
		
		if(focusProblem.contains( MAIN )){
			checkProblemAnswer(answer);
		} else {
			checkSubAnswer(answer);
		}
		/* debug */
		//checkString(checkSubProblemList.toArray());
		nextProblem();
		
		if(indexOfSubProblem == 0) kyojiFlag = true;
		
		return kyojiFlag;
	}
	
	private void setProblem(String PROBLEM_TYPE){
		String tmpString = "";
		answerList.clear();
		
		try{
			FileReader fileReader = new FileReader(new File(SYSTEM_PASS + PROBLEM_TYPE + "/" + focusProblem + "/" + focusProblem +".csv"));
			BufferedReader bReader = new BufferedReader(fileReader);

			char[] check = new char[SUB_PROBLEM_MAX];
			while( (tmpString = bReader.readLine()) != null){
				String[] answerData = tmpString.split(",");
				
				if(answerData[0].contains(PROBLEM_TYPE)) problemImageURI = SYSTEM_PASS + PROBLEM_TYPE + "/" + focusProblem + "/" + answerData[0];
				if(answerData[0].contains(KYOJI)) kyojiImageURI = SYSTEM_PASS + KYOJI + "/" + answerData[0];
				
				for(int i = 3 ,j = 0 ; i < answerData.length ; i++,j++){
					check[j] = answerData[i].charAt(0);
				}
				
				answerList.add(new AnswerModel(answerData[1],answerData[2].charAt(0),check) );
			}
						
		}catch(Exception e){
			e.printStackTrace();
		}

		
		//debug
		//problemsList.add(problemImageURI);
		//checkString(checkArrayObjects);
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
	
	/*
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
	*/
}
