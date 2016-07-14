package learningModel;

public class AnswerModel {
	String answer;
	boolean check;
	boolean[] diagnostic = new boolean[24];
	
	AnswerModel(String answer, boolean check, boolean[] diagnostic){
		this.answer = answer;
		this.check = check;
		this.diagnostic = diagnostic;
	}
}
