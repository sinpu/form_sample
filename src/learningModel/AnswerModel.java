package learningModel;

public class AnswerModel {
	String answer;
	char check;
	char[] diagnostic = new char[24];
	
	AnswerModel(String answer, char check, char[] diagnostic){
		this.answer = answer;
		this.check = check;
		this.diagnostic = diagnostic;
	}
}
