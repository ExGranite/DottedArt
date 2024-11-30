package da.jade.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class GalleryGui  extends JFrame {
	public UserGui gui;
	public SessionAgent sa;
	public CreateTable ct;
	private JTextField dayField;
	JPanel p;
//	SessionAgent sa
	GalleryGui(Connection c, SessionAgent s, int user_id, String u_type) {
//		super(sa.getLocalName());
//		myAgent = sa;
		
		p = new JPanel(new GridLayout(7, 1));
		setTitle("DottedArt Gallery");
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JLabel extra = new JLabel();
        
        JLabel message = new JLabel();
        message.setText("Welcome to Gallery!!");
        p.add(message);
        p.add(extra);
        p.add(extra);
        
//        message = new JLabel();
//        message.setText("Owned Art!!");
//        p.add(message);
//        p.add(extra);

	    if (u_type.equals("Artist")) {
	    	message.setText("Artwork");
	        p.add(message);
	        ct = new CreateTable();
	        String sql = "SELECT * FROM ARTWORK";
			ResultSet rs = ct.getData(c, sql);
			int  count = 0;
			try {
				while (rs.next()) {
					count += 1;
				}
			} catch (Exception e) {
				
			}
			String[] name = {"id", "name", "genre", "price"};
//			sql = "SELECT * FROM ARTWORK WHERE owner_id = '"+user_id+"'";
			rs = ct.getData(c, sql);
			String [][] data = new String[count][4];
			int i = 0;
			try {
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("art_id"));
					String n = rs.getString("name");
					String g = rs.getString("genre");
					String p = rs.getString("price");
//					String st = rs.getString("status");
					data[i][0] = id;
					data[i][1] = n;
					data[i][2] = g;
					data[i][3] = p;
//					data[i][4] = st;
					i += 1;
				}
			} catch (Exception e) {
				
			}
	        
	        JTable j = new JTable(data, name);
	        j.setBounds(40, 20, 600, 700);
	        JScrollPane sp = new JScrollPane(j);
	        p.add(sp);
	        
	        p.add(extra);
	    } else {
        message.setText("Unsold Artwork");
        p.add(message);
        ct = new CreateTable();
        String sql = "SELECT * FROM ARTWORK WHERE STATUS = 'Unsold';";
		ResultSet rs = ct.getData(c, sql);
		int  count = 0;
		try {
			while (rs.next()) {
				count += 1;
			}
		} catch (Exception e) {
			
		}
		String[] name = {"id", "name", "genre", "price", "status"};
//		sql = "SELECT * FROM ARTWORK WHERE owner_id = '"+user_id+"'";
		rs = ct.getData(c, sql);
		String [][] data = new String[count][5];
		int i = 0;
		try {
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("art_id"));
				String n = rs.getString("name");
				String g = rs.getString("genre");
				String p = rs.getString("price");
				String st = rs.getString("status");
				data[i][0] = id;
				data[i][1] = n;
				data[i][2] = g;
				data[i][3] = p;
				data[i][4] = st;
				i += 1;
			}
		} catch (Exception e) {
			
		}
        
        JTable j = new JTable(data, name);
        j.setBounds(40, 20, 600, 700);
        JScrollPane sp = new JScrollPane(j);
        p.add(sp);
        
        p.add(extra);
		
		
        
		
	    JButton submit = new JButton("BUY >>");
	    submit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(submit);

	    submit.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
          	  	PaymentGui startgui = new PaymentGui(c, s,user_id, u_type);
          	  	startgui.display();
	    	}
	    });
	    
	    message.setText("Sold Artwork");
        p.add(message);
        ct = new CreateTable();
        sql = "SELECT * FROM ARTWORK WHERE STATUS = 'Sold';";
		rs = ct.getData(c, sql);
		count = 0;
		try {
			while (rs.next()) {
				count += 1;
			}
		} catch (Exception e) {
			
		}
//		String[] name = {"id", "name", "genre", "price", "status"};
//		sql = "SELECT * FROM ARTWORK WHERE owner_id = '"+user_id+"'";
		rs = ct.getData(c, sql);
		data = new String[count][5];
		i = 0;
		try {
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("art_id"));
				String n = rs.getString("name");
				String g = rs.getString("genre");
				String p = rs.getString("price");
				String st = rs.getString("status");
				data[i][0] = id;
				data[i][1] = n;
				data[i][2] = g;
				data[i][3] = p;
				data[i][4] = st;
				i += 1;
			}
		} catch (Exception e) {
			
		}
        
        j = new JTable(data, name);
        j.setBounds(40, 20, 600, 700);
        sp = new JScrollPane(j);
        p.add(sp);
        
        p.add(extra);
	    }

		JButton back = new JButton("<< BACK");
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(back);
        add(p, BorderLayout.CENTER);
        
	    back.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
            	JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
          	  	UserGui gui = new UserGui(c, s, user_id, u_type);
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
