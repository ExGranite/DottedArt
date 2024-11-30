package da.jade.project;

import java.awt.Component;
import java.sql.*;
import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGui extends JFrame {
	public UserGui gui;
	public SessionAgent sa;
	public CreateTable ct;
	JPanel login;
	JLabel email_label, password_label, message;
	JTextField email_text;
	JPasswordField password_text;
	JButton submit, back;
	public LoginGui(Connection c, SessionAgent s) {
//		Container pane = getContentPane();
		login = new JPanel(new GridLayout( 5, 2));
		setTitle("DottedArt Gallery");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
//        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		email_label = new JLabel();
	    email_label.setText("Email :");
	    email_label.setAlignmentY(Component.CENTER_ALIGNMENT);
	    login.add(email_label);
	    email_text = new JTextField();
	    email_text.setPreferredSize( new Dimension( 200, 24 ) );
	    email_text.setAlignmentY(Component.CENTER_ALIGNMENT);
	    login.add(email_text);
	    
	    password_label = new JLabel();
	    password_label.setText("Password :");
	    password_label.setAlignmentX(Component.CENTER_ALIGNMENT);
	    login.add(password_label);
	    password_text = new JPasswordField();
	    password_text.setAlignmentX(Component.CENTER_ALIGNMENT);
	    login.add(password_text);
	    
	    message = new JLabel();
	    JLabel extra = new JLabel();
//	    message.setAlignmentX(Component.CENTER_ALIGNMENT);
	    login.add(extra);
	    login.add(message);

	    back = new JButton("<< BACK");
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    login.add(back);
	    
	    submit = new JButton("SUBMIT >>");
	    submit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    login.add(submit);
	    
//	    JPanel a =new JPanel();
//        a.setLayout(new GridLayout(3, 2));
//        
//        a.add(email_label);
////        add(login, BorderLayout.WEST);
//        a.add(email_text);
////        add(login, BorderLayout.EAST);
//        add(a, BorderLayout.NORTH);
//        
//        JPanel b =new JPanel();
//        b.setLayout(new GridLayout(3, 2));
//        b.add(password_label);
////        add(login, BorderLayout.WEST);
//	    b.add(password_text);
////        add(login, BorderLayout.WEST);
//	    add(b, BorderLayout.CENTER);
//	    JPanel c =new JPanel();
//        c.setLayout(new GridLayout(3, 2));
//	    message = new JLabel();
//	    c.add(message);
//        add(login, BorderLayout.CENTER);
//	    c.add(submit);
//        add(login, BorderLayout.CENTER);
//	    c.add(back);
        add(login, BorderLayout.CENTER);
	    
//	    pack();
	    back.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
            	JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
          	  	MainGui startgui = new MainGui(c, s);
          	  	startgui.display();
            }
	    });
	    
	    submit.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		String email = email_text.getText();
	    		char [] password = password_text.getPassword();
	    		String pass = "";
	    		for (int i  = 0; i < password.length; i++) {
	    			pass += password[i];
	    		}
	    		String sql = "SELECT * FROM ACCOUNT WHERE EMAIL = '"+email+"' AND PASSWORD = '"+pass+"'";
	    		ct = new CreateTable();
	    		ResultSet rs = ct.getData(c, sql);
	    		String newLine = System.getProperty("line.separator");
//	    		email.trim().equals("admin")
	    		try {
	    		if (rs.next()) {
	    			int user_id = rs.getInt("user_id");
	    			String type = rs.getString("user_type");
	    			JComponent comp = (JComponent) e.getSource();
	          	  	Window win = SwingUtilities.getWindowAncestor(comp);
	          	  	win.dispose();
//	          	  
	    			gui = new UserGui(c, s, user_id, type);
	    			gui.display();
	    		} else {
	    			message.setText("User not found. Please register.");
	    		}
	    		} catch (Exception er) {
	    			er.printStackTrace();
	    		}
	    	}
	    });
	}
//	public static void main(String[] args) {
////		new LoginGui(new SessionAgent());
//	}
	// @Override
//	public void actionPerformed(ActionEvent ae) {
//		String userName = userName_text.getText();
//		String password = password_text.getPassword();
//		String newLine = System.getProperty("line.separator");
//		if (userName.trim().equals("admin") && password.trim().equals("seng")) {
//			message.setText("Hello" + userName + newLine + "Welcome to DottedArt");
//			gui = new UserGui(sa);
//			gui.display();
//		} else {
//			message.setText("User not found. Please register.");
//		}
//	}
	public void display() {
//		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
	};
}
