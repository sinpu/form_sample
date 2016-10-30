package learningModel;

import java.util.ArrayList;

public class AnswerModel {
	private String answer; /* 選択肢 */
	private boolean check; /* T/F */
	private int[] diagnostic = new int[24]; /* Link */
	private ArrayList<String> kyojiList = new ArrayList<String>();
	
	/* import Answer */
	public AnswerModel(String answer, char check, char[] diagnostic, ArrayList<String> kyoji){
		this.answer = answer;
		
		if(check == '1'){
			this.check = true;
		}else{
			this.check = false;
		}
		
		int i = 0;
		for(char value : diagnostic){
			if(value == '1'){
				this.diagnostic[i] = 1;
			}else{
				this.diagnostic[i] = 0; 
			}
		i++;
		}
		
		kyojiList = kyoji;
	}
	
	public String getAnswer(){
		return answer;
	}
	
	public boolean getCheckAnswer(){
		return check;
	}
	
	public int[] getSubList(){
		return diagnostic;
	}
	
	public ArrayList<String> getKyojiList(){
		return kyojiList;
	}
	
}
