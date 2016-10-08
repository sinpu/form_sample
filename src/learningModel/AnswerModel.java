package learningModel;

public class AnswerModel {
	private String answer; /* 選択肢 */
	private char check; /* T/F */
	private char[] diagnostic = new char[24]; /* Link */
	
	
	/* import Answer */
	AnswerModel(String answer, char check, char[] diagnostic){
		this.answer = answer;
		this.check = check;

		int i = 0;
		for(char value : diagnostic){
		this.diagnostic[i] = value;
		i++;
		}
	}
	
	public String getAnswer(){
		return answer;
	}
	
	public char getCheckAnswer(){
		return check;
	}
	
	public char[] getSubList(){
		return diagnostic;
	}
	
}
