package da.jade.project;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionAgent extends Agent {
    public MainGui startgui;
    public DatabaseManager db;
    public Connection conn;
    public CreateTable t;
    protected void setup() {
        System.out.println("Session Agent " + getLocalName() + " is ready.");
        DatabaseManager db = new DatabaseManager();
        conn = db.connect();
        t = new CreateTable();
        startgui = new MainGui(conn, this);
    	startgui.display();
    	
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
            	
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = myAgent.receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    if (processRequest(content)) {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent("Session started successfully.");
                        send(reply);
                    } else {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.FAILURE);
                        reply.setContent("Session failed: Invalid credentials.");
                        send(reply);
                    }
                } else {
                    block();
                }
            }
        });
    }

    private boolean processRequest(String requestDetails) {
        // Simulate processing logic for login or registration
        // In a real application, this would involve checking a database
        return requestDetails.equals("valid_credentials");
    }

    protected void takeDown() {
        System.out.println("Session Agent " + getLocalName() + " is terminating.");
    }
}