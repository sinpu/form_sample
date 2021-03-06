package window;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.TextField;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.AbstractListModel;

import learningModel.LearnerModel;
import main.frame.HomePanel;
import main.frame.LearningPanel;
import main.frame.LoginPanel;
import main.frame.SelectProblemPanel;
import menu.MenuListModel;
import menu.TransitionModel;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.sql.Date;

import javax.swing.SwingConstants;


public class MainWindow extends JFrame implements WindowListener{

	private JFrame frame;
	
	/* User Data */
	public LearnerModel user;
		
	//main frame parts
	private JPanel home;
	private SelectProblemPanel select;
	private JPanel login;
	private LearningPanel study;
	
	private JSplitPane splitPane;
	private JTextField problemSize;
	
	private final static String digiUrl = "http://www.digicre.net/sinpu/cai/caiDataInput.php";

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public MainWindow() {
		//System.getProperty("user.name")
		user = new LearnerModel(System.getProperty("user.name"),"0000", "1111");

		initialize();		
	}
	
	public LearnerModel getUser(){
		return user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = this;
	

		frame.setBounds(100, 100, 900, 620);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		//login 
		login = new LoginPanel(this);
		frame.getContentPane().add(login);
		
		//menu & mainFrame
		splitPane = new JSplitPane();
		
		//main frame view
		/* home */
		home = new HomePanel(this);
		splitPane.setRightComponent(home);		
		home.setLayout(new GridLayout(2, 3, 0, 0));

		/* learning */
		select = new SelectProblemPanel(this);
		study = new LearningPanel(this);
		study.setLayout(new BoxLayout(study, BoxLayout.Y_AXIS));
		
		/* record */
		
		//menu view
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		splitPane.setLeftComponent(panel_1);
		
		MenuListModel menu = new MenuListModel(this);
		JButton[] menuBtn = new JButton[TransitionModel.values().length];
		int i = 0;
		for(TransitionModel data : TransitionModel.values()){
			menuBtn[i] = new JButton(data.toString()); 
			menuBtn[i].setMaximumSize(new Dimension(Short.MAX_VALUE, menuBtn[i].getMaximumSize().height));
			menuBtn[i].addActionListener(menu);
			panel_1.add(menuBtn[i]);
			i++;
		}
		JPanel space = new JPanel();
		FlowLayout flowLayout = (FlowLayout) space.getLayout();
		flowLayout.setVgap(200);
		panel_1.add(space);
		
		problemSize = new JTextField();
		panel_1.add(problemSize);
		problemSize.setEditable(false);
		problemSize.setBackground(null);
		problemSize.setBorder(null);
		problemSize.setFont(new Font("Dialog", Font.ITALIC, 33));
		problemSize.setHorizontalAlignment(JTextField.CENTER);
		problemSize.setText("");

		
		/* 使って見たが、listenerが理想的ではない
		JList list = new JList();
		list.setModel(new MenuListModel());
		panel_1.add(list);
		*/
	}
	
	/* login後呼ばれる関数 */
	public void startSystem(String id,String pass){
		user.setID(id);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(splitPane);
		frame.getContentPane().revalidate();
	}
	
	//change display panel
	public void changePanel(TransitionModel display){
		
		switch (display) {
		case Home:
			splitPane.setRightComponent(home);
			break;

		case Subject:
			splitPane.setRightComponent(select);
			break;

		case Study:
			if( select.getSelection().isEmpty() ){
				study.setStudyType(TransitionModel.Study);
				study.start("problem01");
			}else{
				study.setStudyType(TransitionModel.Subject);
				study.start(select.getSelection());
				select.resetSelection();
			}
			splitPane.setRightComponent(study);
			break;
			
		case Record:
			JOptionPane.showMessageDialog(this, "Comming Soon!");
			break;
			
		case Exit:
			if( showFinishDialog() ){
				recordData();
				System.exit(0);
			}
			break;
			
		default:
			break;
		}
		
	}
	
	private void recordData(){
		user.setEndTime();
		
		StringBuffer urlBuffer = new StringBuffer();
		
		String startTime = user.getStartTime().toString();
		String endTime = user.getEndTime().toString();
		String userid = user.getID();
		String userData = user.getDataToString();
		Double studyTime = user.reqStudyTime();
		
		System.out.println(startTime);
		System.out.println(endTime);
		System.out.println(studyTime);
		System.out.println(userid);
		System.out.println(userData);
		
		urlBuffer.append(digiUrl);
		urlBuffer.append("?");
		urlBuffer.append("USER=");
		urlBuffer.append(userid);
		
		urlBuffer.append("&");
		urlBuffer.append("DATA=");
		
		urlBuffer.append("START::");
		urlBuffer.append(startTime.replace(" ", ":"));
		urlBuffer.append("<>");
		
		urlBuffer.append("END::");
		urlBuffer.append(endTime.replace(" ", ":"));
		urlBuffer.append("<>");
		
		urlBuffer.append("STUDY_TIME::");
		urlBuffer.append(studyTime);
		urlBuffer.append("<>");
		
		urlBuffer.append("DATA::");
		urlBuffer.append(userData);

		HttpURLConnection connection;
		
		try {
			URL url = new URL( urlBuffer.toString() );
			connection = (HttpURLConnection)url.openConnection();
			
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("GET");
			
			BufferedReader bReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"JISAutoDetect"));
			String tmp;
			while ( null != (tmp = bReader.readLine())){
				System.out.println("data::" + tmp);
			}
			
			connection.disconnect();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void updateSize(String data){
		problemSize.setText(data);		
	}

	private boolean showFinishDialog(){
		String[] selects = { "Exit" , "Return" };
		
		int ans = JOptionPane.showOptionDialog(this, "Exit System?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, selects, selects[1]);
		
		
		if( ans == 0 ){
			return true;
		}
		
		return false;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		if( showFinishDialog() ){
			recordData();
			System.exit(EXIT_ON_CLOSE);
		}
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		/* Closed にてExitを行ったら終了せんかった…  disposeの結果で終了するときらしい*/
		recordData();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
