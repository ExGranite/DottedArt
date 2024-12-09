package da.jade.project;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class SessionAgent extends Agent {
    public MainGui startgui;
    public DatabaseManager db;
    public Connection conn;
    public CreateTable t;
    int payCount = 0;
    boolean paydone = false;
    protected void setup() {
        System.out.println("Session Agent " + getLocalName() + " is ready.");
        DatabaseManager db = new DatabaseManager();
        conn = db.connect();
        t = new CreateTable();
        startgui = new MainGui(conn, this);
    	startgui.display();
    	
    }

    void login (Connection c, SessionAgent s, ActionEvent e) {
    	JComponent comp = (JComponent) e.getSource();
  	  	Window win = SwingUtilities.getWindowAncestor(comp);
  	  	win.dispose();
        LoginGui gui = new LoginGui(c, s);
        gui.display();
        
    }

    void register (Connection c, SessionAgent s, ActionEvent e) {
    	JComponent comp = (JComponent) e.getSource();
  	  	Window win = SwingUtilities.getWindowAncestor(comp);
  	  	win.dispose();
        RegisterGui gui = new RegisterGui(c, s);
        gui.display();   
    }
    
    void paySubmit (String sql) {
    	addBehaviour(new SimpleBehaviour(this) {
            public void action() {
            	switch (payCount) {
            	case 0:
            		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            		try {
						msg.setContent(sql);
					} catch (Exception e) {
						e.printStackTrace();
					}
            		msg.addReceiver(new AID("pagent", AID.ISLOCALNAME));
            		send(msg);
            		payCount = 1;
            	break;
            	case 1:
            		msg = blockingReceive();
            		System.out.println(msg);
            		payCount = 2;
            	break;
            	case 2:
            		paydone = true;
            	break;
            }
            }
            public boolean done() {return paydone;}
    });
    }
    void add (String sql) {
    	addBehaviour(new SimpleBehaviour(this) {
            public void action() {
            	switch (payCount) {
            	case 0:
            		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            		try {
						msg.setContent(sql);
					} catch (Exception e) {
						e.printStackTrace();
					}
            		msg.addReceiver(new AID("aagent", AID.ISLOCALNAME));
            		send(msg);
            		payCount = 1;
            	break;
            	case 1:
            		msg = blockingReceive();
            		System.out.println(msg);
            		payCount = 2;
            	break;
            	case 2:
            		paydone = true;
            	break;
            }
            }
            public boolean done() {return paydone;}
    });
    }
    void loadGallery (Connection c, SessionAgent s, int user_id, String u_type, ActionEvent e) {
    	addBehaviour(new SimpleBehaviour(this) {
            public void action() {
            	switch (payCount) {
            	case 0:
            		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            		String sql;
            		if (u_type.equals("Artist")) {
            			sql = "SELECT * FROM ARTWORK";
            		} else {
            			sql = "SELECT * FROM ARTWORK WHERE STATUS = 'Unsold';SELECT * FROM ARTWORK WHERE STATUS = 'Sold'";
            		}
            		try {
						msg.setContent(sql);
					} catch (Exception e) {
						e.printStackTrace();
					}
            		msg.addReceiver(new AID("cagent", AID.ISLOCALNAME));
            		send(msg);
            		payCount = 1;
            	break;
            	case 1:
            		msg = blockingReceive();
            		System.out.println(msg);
            		payCount = 2;
            	break;
            	case 2:
            		paydone = true;
            		JComponent comp = (JComponent) e.getSource();
              	  	Window win = SwingUtilities.getWindowAncestor(comp);
              	  	win.dispose();
              	  	GalleryGui gui = new GalleryGui(c, s, user_id, u_type);
              	  	gui.display();
            		
            	break;
            }
            }
            public boolean done() {return paydone;}
    });
    }
}