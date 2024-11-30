package da.jade.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainGui extends JFrame {
	public MainGui(Connection c, SessionAgent s) {
		setTitle("DottedArt Gallery");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel mainButton = new JPanel();
        mainButton.setLayout(new GridLayout(3, 2));
        JButton loginButton = new JButton("Login");
        mainButton.add(loginButton);
        JButton registerButton = new JButton("Register");
        mainButton.add(registerButton);
        add(mainButton, BorderLayout.CENTER);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
                LoginGui gui = new LoginGui(c, s);
                gui.display();
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComponent comp = (JComponent) e.getSource();
          	  	Window win = SwingUtilities.getWindowAncestor(comp);
          	  	win.dispose();
                RegisterGui gui = new RegisterGui(c, s);
                gui.display();
            }
        });
	}
	public void display() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
//        SwingUtilities.invokeLater(() -> {
//        	MainGui gui = new MainGui();
//            gui.setVisible(true);
//        });
    }
}
