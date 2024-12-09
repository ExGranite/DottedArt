package da.jade.project;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.sql.*;

public class PaymentAgent extends Agent {
    private int actionCount = 0;

    protected void setup() {
        System.out.println("Payment Agent " + getLocalName() + " is ready.");
        DatabaseManager db = new DatabaseManager();
        Connection c = null;
        CreateTable ct = new CreateTable();
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
            	switch (actionCount) {
            	case 0:
            		ACLMessage msg = blockingReceive();
            		if (msg != null) {
            			try {
							String sql = msg.getContent();
							ResultSet rs = ct.getData(c, sql);
							if (rs.next()) {
				    			actionCount = 1;
				    			}
						} catch (Exception e) {
							e.printStackTrace();
						}
            		}
            		actionCount = 1;
            	case 1:
            		msg = new ACLMessage(ACLMessage.REQUEST);
            		msg.setContent("Success!! Go to Gallery.");
            		msg.addReceiver(new AID("sagent", AID.ISLOCALNAME));
            		send(msg);
            		actionCount = 0;
            	}
            	
            }
//                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
//                ACLMessage msg = myAgent.receive(mt);
//                if (msg != null) {
//                    String content = msg.getContent();
//                    if (processPayment(content)) {
//                        ACLMessage reply = msg.createReply();
//                        reply.setPerformative(ACLMessage.INFORM);
//                        reply.setContent("Payment successful for amount: " + transactionAmount);
//                        send(reply);
//                    } else {
//                        ACLMessage reply = msg.createReply();
//                        reply.setPerformative(ACLMessage.FAILURE);
//                        reply.setContent("Payment failed: Invalid card details.");
//                        send(reply);
//                    }
//                } else {
//                    block();
//                }
            
        });
    }

//    public void processPayment(String paymentDetails) {
//        String[] details = paymentDetails.split(",");
//        if (details.length == 4) {
//            transactionAmount = 100.00;
//            System.out.println("Processing payment of $" + transactionAmount + " for card " + details[0]);
//            return true;
//        }
//    }

    protected void takeDown() {
        System.out.println("Payment Agent " + getLocalName() + " is terminating.");
    }
}