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
	private HashMap<String,AnswerModel> answerMap = new HashMap<String,AnswerModel>();
	private ArrayList<String> answerList = new ArrayList<String>(); 
	 /* 大問題のURIリスト */
	private ArrayList<String> problemsList = new ArrayList<String>();
	 /* 少問題のURIリスト */
	private ArrayList<String> subProblemList = new ArrayList<String>();
	 /* 教示カードのURIリスト */
	private ArrayList<String> kyojiList = new ArrayList<String>(); 
	private ArrayList<String> checkProblemList = new ArrayList<String>();
	private ArrayList<String> giveKyojiList = new ArrayList<String>();
	
	//problem
	private String problemImageURI = new String();
	private String answerTextURI = new String();
	private String kyojiImageURI = new String();
	private String focusProblem;
	
	//answer
	private String[] answerImageURI = new String[8];

	private String SYSTEM_PASS = "src/learning_system/";
	private String PROBLEM_LIST_FILE = "problem.csv";
	private String SUB_PROBLEM_LIST_FILE = "subkyoji.csv";
	private String MAIN = "problem";
	private String SUB = "sub";
	private String KYOJI = "kyoji";
	
	private int PROBLEM_FILE_INDEX = 0;
	private int ANSWER_FILE_INDEX = 1;
	
	private int indexOfProblem;
	private int index = 99;
	private int now = 0;
	private boolean killFrag;
	
	public static final int SUB_PROBLEM_MAX = 24;
	int[] subCheckList = new int[SUB_PROBLEM_MAX];

	
	/* debug */
	ArrayList<Object> checkArrayList = new ArrayList<Object>();
	
	
	public ProblemModel(){
		String tmpString = "";
		String listString = "";
		indexOfProblem = 0;
		index = 0;
		killFrag = true;
		resetSubCheckList();
		
		// set problem list
		try{
			FileReader fReader = new FileReader(new File(SYSTEM_PASS + PROBLEM_LIST_FILE));
			BufferedReader bReader = new BufferedReader(fReader);

			while( (tmpString = bReader.readLine()) != null){
				listString = tmpString;
			}

			String[] problem = listString.split("\t");
			
			
			for(String tmp : problem){
				problemsList.add(tmp);
			}
			focusProblem = problemsList.get(PROBLEM_FILE_INDEX);
			checkProblemList.add(focusProblem);
			
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
			
			String[] data = dataString[3].split("\t");
			for(String tmp : data){
				subProblemList.add(tmp);
			}
			
			data = dataString[4].split("\t");
			for(String tmp : data){
				kyojiList.add(tmp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/* debug */
		//checkString(kyojiList.toArray());
		setProblem( MAIN ,focusProblem);
	}
	
	public int getProblemLength(){
		return (checkProblemList.size() + problemsList.size());
	}
	
	public int getProblemIndex(){
		return index;
	}
	
	public String getProblem(){
		return problemImageURI;
	}

	public ArrayList<String> getKyojiURIlist(){
		return giveKyojiList;
	}

	
	public ArrayList<String> getAnswer(){
		return answerList;
	}
	
	public String getProblemName(){
		return focusProblem;
	}
	
	/* 学習記録の出力 */
	public void saveLearningData(){
		
	}
	
	public boolean nextProblem(){
		String problemType = SUB;

		killProblem();
		resetSubCheckList();
		
		if(checkProblemList.isEmpty()) {
			indexOfProblem++;
			index = 0;
			/* finish condition */
			if(indexOfProblem == problemsList.size()) return false; 
			checkProblemList.add(problemsList.get(indexOfProblem));
		}
		
		now = index;
		if( now >= checkProblemList.size()) now = checkProblemList.size() -1;
		focusProblem = checkProblemList.get(now);
		//System.out.println("now :" + focusProblem + ":" + index);
		
		index--;
		if(index < 0) index = checkProblemList.size() -1;
		if(discrimeTypeMAIN()) problemType = MAIN;

		/* 検証用
		for(String name : checkProblemList){
			System.out.println(name);
		}
		*/
		
		setProblem(problemType,focusProblem);
		return true;
	}
	
	/* 使ってないね */
	public void previosProblem(){		
		if(indexOfProblem < 0) indexOfProblem = problemsList.size() - 1;
	}
	
	/* main problem answer check method */
	public boolean checkProblemAnswer(String answer){
		if("".equals(answer)) return false;
		
		AnswerModel answerModel = answerMap.get(answer);
		boolean check = answerModel.getCheckAnswer();
		if(!check){ //wrong
			int[] value = answerModel.getSubList();
			for(int i = 0; i < SUB_PROBLEM_MAX; i++){
				subCheckList[i] += value[i];
			}
			killFrag = false;
		}
		
		return check;
	}
	
	private void killProblem(){
		if(killFrag) checkProblemList.remove(now);
		killFrag = true;
	}
	
	private void resetSubCheckList(){
		for(int i = 0 ; i < SUB_PROBLEM_MAX ; i++){
			subCheckList[i] = 0; 
		}
	}
	
	/*  */
	private boolean contaionString(String uri,ArrayList<String> list){
		boolean stillFlag = false;
		
		for(String URI : list){
			if(URI.equals(uri)) stillFlag = true;
		}
		
		return stillFlag;
	}
	
	/* discrimination problem type */
	public boolean discrimeTypeMAIN(){
		if(focusProblem.contains( MAIN )) return true;
		
		return false;
	}
	
	public void setSubProblem(){
		if(!discrimeTypeMAIN()) return ;
		
		int[] point = checkSubProblem();
		for(int i = 0 ; i < 3 ; i++){
			if(subCheckList[point[i]] == 0) continue;
			String tmpURI = subProblemList.get(point[i]);
			if(!contaionString(tmpURI, checkProblemList)){
				checkProblemList.add(tmpURI);
			}
			setProblem(SUB ,tmpURI);
			makeKyojiList();
			
			System.out.println(giveKyojiList.size() +":"+ tmpURI);
		}
		index = checkProblemList.size() -1 ;
	}
	
	private int[] checkSubProblem(){
		int[] n = { 0, 0, 0};
		int[] k = { 0, 0, 0};
		for(int i = 0 ; i < SUB_PROBLEM_MAX ; i++){
			if( subCheckList[i] > n[0] ){
				n[0] = subCheckList[i];
				k[0] = i;
				continue;
			}
			if( subCheckList[i] > n[1] ){
				n[1] = subCheckList[i];
				k[1] = i;
				continue;
			}
			if( subCheckList[i] > n[2] ){
				n[2] = subCheckList[i];
				k[2] = i;
				continue;
			}			
		}
		return k;
	}
	
	private void makeKyojiList(){
		/* まだ要らない
		 *  giveKyojiList.clear();
		 */
		String[] tmp = kyojiImageURI.split("<>");
		for(String kyoji : tmp){
			String kyojiURI = SYSTEM_PASS + KYOJI + "/" + kyoji;
			if( !contaionString(kyojiURI,giveKyojiList) )giveKyojiList.add(kyojiURI);
		}
	}
	
	/* import Problem and setting Selections */
	private void setProblem(String PROBLEM_TYPE, String fileName){
		String tmpString = "";
		answerMap.clear();
		answerList.clear();
		
		try{
			FileReader fileReader = new FileReader(new File(SYSTEM_PASS + PROBLEM_TYPE + "/" + fileName + "/" + fileName +".csv"));
			BufferedReader bReader = new BufferedReader(fileReader);

			char[] check = new char[SUB_PROBLEM_MAX];
			while( (tmpString = bReader.readLine()) != null){
				String[] answerData = tmpString.split("\t");
				
				if(answerData[0].contains(PROBLEM_TYPE)) problemImageURI = SYSTEM_PASS + PROBLEM_TYPE + "/" + fileName + "/" + answerData[0];
				if(answerData[0].contains(KYOJI)) kyojiImageURI =  answerData[0];
				
				for(int i = 3 ,j = 0 ; i < answerData.length ; i++,j++){
					check[j] = answerData[i].charAt(0);
				}
				
				answerMap.put(answerData[1],new AnswerModel(answerData[1],answerData[2].charAt(0),check) );
				answerList.add(answerData[1]);
			}
						
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* デバッグ用メソッド */
	private void checkString(Object[] tmp){
		JFrame answerWindow = new JFrame();
		answerWindow.setBounds(1000, 100, 300, 600);
		answerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
