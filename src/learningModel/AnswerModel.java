package learningModel;

public class AnswerModel {
	private String answer;
	private char check;
	private char[] diagnostic = new char[24];
	
	AnswerModel(String answer, char check, char[] diagnostic){
		this.answer = answer;
		this.check = check;
		this.diagnostic = diagnostic;
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
