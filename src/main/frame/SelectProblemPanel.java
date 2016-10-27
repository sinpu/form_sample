package main.frame;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import menu.TransitionModel;

import window.MainWindow;

public class SelectProblemPanel extends JPanel implements ActionListener{

	private MainWindow mainWindow;
	
	private String SYSTEM_PASS = "src/learning_system/";
	private String PROBLEM_LIST_FILE = "problem.csv";

	private JPanel problemImagePanel;
	
	private String selectProblem = "";
	
	
	public SelectProblemPanel(MainWindow mainWindow){
		super();
		
		this.mainWindow = mainWindow;
		
		problemImagePanel = new JPanel();
		
		String dataString = "";
		
		try{
			FileReader fReader = new FileReader(new File(SYSTEM_PASS + PROBLEM_LIST_FILE));
			BufferedReader bReader = new BufferedReader(fReader);

			String tmpString = "";
			while( (tmpString = bReader.readLine()) != null){
				dataString = tmpString;
			}

			String[] problem = dataString.split("\t");
			
			
			for(String tmp : problem){
				JButton button = new JButton();
				String[] name = tmp.split(",");
				button.setText( name[0] );
				button.addActionListener(this);
				
				ImageIcon icon = new ImageIcon(SYSTEM_PASS + "problem/" + name[0] + "/" + name[0] + ".jpg");
				Image miniImage = icon.getImage().getScaledInstance((int)(icon.getIconWidth() * 0.3) ,(int)(icon.getIconHeight() * 0.3) , Image.SCALE_SMOOTH);
				icon.setImage( miniImage );
				button.setIcon( icon );
				
				problemImagePanel.add( button );
			}

			problemImagePanel.setLayout(new GridLayout( (int)(1 + problem.length / 2), 2, 0, 0));
			this.add(problemImagePanel);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public String getSelection(){
		return selectProblem;
	}
	
	public void resetSelection(){
		selectProblem = "";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton tmp = (JButton)e.getSource();
		selectProblem = tmp.getText();
		
		mainWindow.changePanel(TransitionModel.Study);
	}

	
}
