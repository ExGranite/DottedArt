package da.jade.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UserGui  extends JFrame {
	public UserGui gui;
	public SessionAgent sa;
	public CreateTable ct;
	private JTextField dayField;
	JPanel p;
//	SessionAgent sa
	UserGui(Connection c, SessionAgent s, int user_id, String u_type) {
//		super(sa.getLocalName());
//		myAgent = sa;
		
		p = new JPanel(new GridLayout(7, 1));
		setTitle("DottedArt Gallery");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JLabel extra = new JLabel();
        
        JLabel message = new JLabel();
        message.setText("Welcome to user dashboard!!");
        p.add(message);
//        p.add(extra);
//        p.add(extra);
        
        message = new JLabel();
        message.setText("Owned Art!!");
        p.add(message);
//        p.add(extra);
        
        ct = new CreateTable();
        String sql;
        if (u_type.equals("Artist")) {
        	sql = "SELECT * FROM ARTWORK WHERE artist_id = '"+user_id+"';";
        } else {
        	sql = "SELECT * FROM ARTWORK WHERE owner_id = '"+user_id+"';";
        }
		ResultSet rs = ct.getData(c, sql);
		int  count = 0;
		try {
			while (rs.next()) {
				count += 1;
			}
		} catch (Exception e) {
			
		}
		String[] name = {"name", "genre", "price"};
//		sql = "SELECT * FROM ARTWORK WHERE owner_id = '"+user_id+"'";
		rs = ct.getData(c, sql);
		String [][] data = new String[count][3];
		int i = 0;
		try {
			while (rs.next()) {
				String n = rs.getString("name");
				String g = rs.getString("genre");
				String p = rs.getString("price");
				data[i][0] = n;
				data[i][1] = g;
				data[i][2] = p;
				i += 1;
			}
		} catch (Exception e) {
			
		}
        
        JTable j = new JTable(data, name);
        j.setBounds(40, 20, 600, 700);
        JScrollPane sp = new JScrollPane(j);
        p.add(sp);
        
//        p.add(extra);
		
		
        
		
		JButton back = new JButton("<< LOGOUT");
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(back);
	    
	    if (u_type.equals("Artist")) {
	    JButton add_a = new JButton("ADD");
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(add_a);
	    
	    add_a.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
            	JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
          	  	AddGui startgui = new AddGui(c, s, user_id, u_type);
          	  	startgui.display();
            }
	    });}
	    
	    JButton submit = new JButton("GALLERY >>");
	    submit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(submit);
        
        add(p, BorderLayout.CENTER);
        
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
	    		JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
          	  	GalleryGui gui = new GalleryGui(c, s, user_id, u_type);
          	  	gui.display();
	    	}
	    });
	}
	public void display() {
//		pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        setVisible(true);
	}
	public static void main(String[] args) {
//		UserGui a = new UserGui();
//		a.display();
	}
}
