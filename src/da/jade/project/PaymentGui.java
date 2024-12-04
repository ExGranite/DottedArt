package da.jade.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class PaymentGui extends JFrame {
	JPanel p;
	public CreateTable ct;
	JLabel message, extra = new JLabel();
	PaymentGui(Connection c, SessionAgent s, int user_id, String u_type) {
		p = new JPanel(new GridLayout( 10, 2));
		setTitle("DottedArt Gallery");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel id = new JLabel();
        id.setText("Which art do you want to buy :");
        JTextField id_t = new JTextField();
        p.add(id); p.add(id_t);
        
        JLabel type = new JLabel();
        type.setText("Card Type :");
        JTextField type_t = new JTextField();
        p.add(type); p.add(type_t);
        
        JLabel num = new JLabel();
        num.setText("Card Number :");
        JTextField num_t = new JTextField();
        p.add(num);
        p.add(num_t);
        
        JLabel name = new JLabel();
        name.setText("Name :");
        JTextField name_t = new JTextField();
        p.add(name);
        p.add(name_t);
        
        JLabel month = new JLabel();
        month.setText("Expiry Month :");
        JTextField month_t = new JTextField();
        p.add(month);
        p.add(month_t);
        
        JLabel year = new JLabel();
        year.setText("Expiry Year :");
        JTextField year_t = new JTextField();
        p.add(year);
        p.add(year_t);
        
        JLabel address = new JLabel();
        address.setText("Billing Address :");
        JTextField address_t = new JTextField();
        p.add(address);
        p.add(address_t);
        
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
          	  	UserGui startgui = new UserGui(c, s, user_id, u_type);
          	  	startgui.display();
            }
	    });
	    
	    submit.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		try {
	    		String id = id_t.getText();
	    		String c_type = type_t.getText();
	    		String c_num = num_t.getText();
	    		String c_name = name_t.getText();
	    		String c_month = month_t.getText();
	    		String c_year = year_t.getText();
	    		String c_add = address_t.getText();
	    		String newLine = System.getProperty("line.separator");
	    		ct = new CreateTable();
	    		String sql = "SELECT art_id FROM ARTWORK WHERE art_id = "+id+" AND status = 'Unsold';";
	    		s.paySubmit(sql);
	    		ResultSet rs = ct.getData(c, sql);
	    		if (rs.next()) {
	    			sql = "UPDATE ARTWORK SET status = 'Sold', owner_id = "+user_id+" WHERE art_id = "+id+";";
	    			ct.editData(c, sql);
	    			message.setText("Success!! Go to Gallery.");
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
