package da.jade.project;

import java.awt.Component;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGui extends JFrame {
	public UserGui gui;
	public SessionAgent sa;
	public CreateTable ct;
	JPanel register;
	JLabel f_label, l_label, type_label, email_label, password_label, message, extra;
	JTextField f_text, l_text, type_text, email_text;
	JPasswordField password_text;
	JButton submit, back;
	public RegisterGui(Connection c, SessionAgent s) {
		register = new JPanel(new GridLayout( 10, 2 ) );
		setTitle("DottedArt Gallery");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        f_label = new JLabel();
	    f_label.setText("First Name :");
	    f_label.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(f_label);
	    f_text = new JTextField();
	    f_text.setPreferredSize( new Dimension( 200, 24 ) );
	    f_text.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(f_text);
	    
	    l_label = new JLabel();
	    l_label.setText("Last Name :");
	    l_label.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(l_label);
	    l_text = new JTextField();
	    l_text.setPreferredSize( new Dimension( 200, 24 ) );
	    l_text.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(l_text);
        
	    type_label = new JLabel();
	    type_label.setText("Artist / Viewer :");
	    type_label.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(type_label);
	    type_text = new JTextField();
	    type_text.setPreferredSize( new Dimension( 200, 24 ) );
	    type_text.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(type_text);
	    
        email_label = new JLabel();
	    email_label.setText("Email :");
	    email_label.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(email_label);
	    email_text = new JTextField();
	    email_text.setPreferredSize( new Dimension( 200, 24 ) );
	    email_text.setAlignmentY(Component.CENTER_ALIGNMENT);
	    register.add(email_text);
	    
	    password_label = new JLabel();
	    password_label.setText("Password :");
	    password_label.setAlignmentX(Component.CENTER_ALIGNMENT);
	    register.add(password_label);
	    password_text = new JPasswordField();
	    password_text.setAlignmentX(Component.CENTER_ALIGNMENT);
	    register.add(password_text);
	    
	    message = new JLabel();
	    register.add(message);
	    extra = new JLabel();
	    register.add(extra);
	    
	    
	    back = new JButton("<< BACK");
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    register.add(back);
	    submit = new JButton("SUBMIT >>");
	    submit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    register.add(submit);

	    add(register, BorderLayout.CENTER);
	    
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
	    		String first = f_text.getText();
	    		String last = l_text.getText();
	    		String u_type = type_text.getText();
	    		String email = email_text.getText();
	    		int user_id = 1;
	    		char [] password = password_text.getPassword();
	    		String pass = "";
	    		for (int i  = 0; i < password.length; i++) {
	    			pass += password[i];
	    		}
	    		try {
	    		ct = new CreateTable();
	    		String sql = "SELECT MAX(user_id) FROM ACCOUNT";
	    		ResultSet rs = ct.getData(c, sql);
	    		if (rs.next()) {
	    			user_id = rs.getInt(1);
	    			System.out.println(user_id);
	    			user_id += 1;
	    		}
	    		sql = "INSERT INTO ACCOUNT (user_id, user_type, fname, lname, email, password) VALUES ("+user_id+", '"+u_type+"', '"+first+"', '"+last+"', '"+email+"', '"+pass+"');";
	    		ct.editData(c, sql);
	    		sql = "SELECT * FROM ACCOUNT WHERE user_id = "+user_id+";";
	    		rs = ct.getData(c, sql);
	    		String newLine = System.getProperty("line.separator");
	    		if (rs.next()) {
	    			message.setText("Registration Successful!! Go back and login.");
	    		} else {
	    			message.setText("Error!! Try Again...");
	    		} } catch (Exception er) {
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
