package da.jade.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AddGui extends JFrame {
	JPanel p;
	public CreateTable ct;
	JLabel message, extra = new JLabel();
	AddGui(Connection c, SessionAgent s, int user_id, String u_type) {
		p = new JPanel(new GridLayout( 8, 2));
		setTitle("DottedArt Gallery");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel name = new JLabel();
        name.setText("Name :");
        JTextField name_t = new JTextField();
        p.add(name); p.add(name_t);
        
        JLabel des = new JLabel();
        des.setText("Description :");
        JTextField des_t = new JTextField();
        p.add(des); p.add(des_t);
        
        JLabel gen = new JLabel();
        gen.setText("Genre :");
        JTextField gen_t = new JTextField();
        p.add(gen);
        p.add(gen_t);
        
        JLabel pr = new JLabel();
        pr.setText("Price :");
        JTextField pr_t = new JTextField();
        p.add(pr);
        p.add(pr_t);
        
        message = new JLabel();
	    p.add(message);
	    extra = new JLabel();
	    p.add(extra);
	    
	    JButton back = new JButton("<< BACK");
	    back.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(back);
	    JButton submit = new JButton("SUBMIT >>");
	    submit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    p.add(submit);
        
	    back.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
            	JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
          	  	GalleryGui startgui = new GalleryGui(c, s, user_id, u_type);
          	  	startgui.display();
            }
	    });
	    
	    submit.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		try {
	    		String name = name_t.getText();
	    		String des = des_t.getText();
	    		String gen = gen_t.getText();
	    		String pr = pr_t.getText();
	    		int art_id = 1;
	    		String newLine = System.getProperty("line.separator");
	    		ct = new CreateTable();
	    		String sql = "SELECT MAX(art_id) FROM ARTWORK";
	    		ResultSet rs = ct.getData(c, sql);
	    		if (rs.next()) {
	    			art_id = rs.getInt(1);
	    			System.out.println(art_id);
	    			art_id += 1;
	    		}
	    		sql = "INSERT INTO ARTWORK (art_id, name, description, genre, tags, price, status, artist_id, owner_id) VALUES ("+art_id+", '"+name+"', '"+des+"', '"+gen+"', '"+gen+"', '"+pr+"', 'Unsold', "+user_id+", "+user_id+");";
	    		ct.editData(c, sql);
	    		sql = "SELECT * FROM ARTWORK WHERE art_id = '"+art_id+"'";
	    		rs = ct.getData(c, sql);
//	    		String newLine = System.getProperty("line.separator");
	    		if (rs.next()) {
	    			message.setText("Successful!! Go back to gallery.");
	    		} else {
	    			message.setText("Error!! Try Again...");
	    		}
	    		} catch (Exception er) {
	    			er.printStackTrace();
	    		}
	    	}
	    });
	    
        add(p, BorderLayout.CENTER);
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
//		PaymentGui a = new PaymentGui();
//		a.display();
	}
}
