package da.jade.project;

import java.sql.Connection;
import java.sql.ResultSet;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ArtworkAgent extends Agent {
	private int actionCount = 0;
    protected void setup() {
        System.out.println("Artwork Agent " + getLocalName() + " is ready.");
        DatabaseManager db = new DatabaseManager();
        Connection c = db.connect();
        CreateTable ct = new CreateTable();
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
            	switch (actionCount) {
            	case 0:
            		ACLMessage msg = blockingReceive();
            		if (msg != null) {
            			try {
							String sql = msg.getContent();
							ct.editData(c, sql);
							actionCount = 1;
						} catch (Exception e) {
							e.printStackTrace();
						}
            		}
//            		actionCount = 1;
            	case 1:
            		msg = new ACLMessage(ACLMessage.REQUEST);
            		msg.setContent("Success!! Go to Gallery.");
            		msg.addReceiver(new AID("sagent", AID.ISLOCALNAME));
            		send(msg);
            		actionCount = 0;
            	}
            	
            }
        });
    }

}