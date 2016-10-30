package learningModel;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import org.eclipse.core.internal.runtime.Log;

import com.ibm.icu.impl.CalendarAstronomer;
import com.ibm.icu.util.Calendar;

public class LearnerModel {
	
	private HashMap<String, ArrayList<String>> recodeMap = new HashMap<String, ArrayList<String>>();
	
	private String name;
	private String id;
	private String pass;
	
	private java.util.Date sTime; //start time of learning
	private java.util.Date eTime; //end time of learning
	
	public LearnerModel(String name,String id,String pass){
		this.name = name;
		this.id = id;
		this.pass = pass;
		
		this.sTime = new java.util.Date();
	}
	
	public String getName(){
		return name;
	}
	
	public String getID(){
		return id;
	}
	
	public String getPASS(){
		return pass;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setPASS(String pass){
		this.pass = pass;
	}
	
	public void setEndTime(){
		this.eTime = new java.util.Date();
	}
	
	public java.util.Date getStartTime(){
		return sTime;
	}
	
	public java.util.Date getEndTime(){
		return eTime;
	}
	
	public double reqStudyTime(){
		/*
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(sTime);

		Calendar endTime = Calendar.getInstance();
		endTime.setTime(eTime);
		*/
	
		
		/* long is mili Sec*/
		long differentTime = eTime.getTime() - sTime.getTime();
		int minites = 1000 * 60;
		double studyTime = (double)( differentTime / minites );
		
		return studyTime;
	}
	
	
	private boolean searchRecord(){
	
		return true;
	}
	
	public void setRecord(String problemName, ArrayList<String> answerList){
		
		
		if( null == recodeMap.get(problemName) || recodeMap.get(problemName).isEmpty()){
			recodeMap.put(problemName, answerList);
		} else {
			for(int i = 1 ; ; i++){
				String fileName = problemName + "(" + i + ")";
				if( null == recodeMap.get(fileName) || recodeMap.get(fileName).isEmpty() ){
					recodeMap.put( fileName , answerList);
					break;
				}
			}
		}
		
	}
	
	public String getDataToString(){
		StringBuffer dataBuffer = new StringBuffer();
		
		dataBuffer.append("name::");
		dataBuffer.append( getName() );
		dataBuffer.append("<>");

		dataBuffer.append("Record::");
		dataBuffer.append("<>");

		for(String key : recodeMap.keySet()){
			dataBuffer.append( key );
			dataBuffer.append("::");
			
			ArrayList<String> ansList = recodeMap.get( key );
			if( null == ansList || ansList.isEmpty() ) break;
			
			for(String answer : ansList){
				dataBuffer.append( answer );
				dataBuffer.append(",");
			}
			int index = dataBuffer.lastIndexOf(",");
			dataBuffer.deleteCharAt( index );
			dataBuffer.append("<>");
		}
		
		return dataBuffer.toString();
	}
	
	

}
