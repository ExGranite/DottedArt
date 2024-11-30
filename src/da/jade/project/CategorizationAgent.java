package da.jade.project;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CategorizationAgent extends Agent {
    
    protected void setup() {
        System.out.println("Categorization Agent " + getLocalName() + " is ready.");

        // Add a behavior to handle categorization requests
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = myAgent.receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    // Process the categorization request
                    String categorizedData = categorizeArtwork(content);
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent(categorizedData);
                    send(reply);
                } else {
                    block();
                }
            }
        });
    }

    private String categorizeArtwork(String artworkDetails) {
        // Simulate categorization logic
        return "Categorized artwork: " + artworkDetails + " as 'Abstract'";
    }

    protected void takeDown() {
        System.out.println("Categorization Agent " + getLocalName() + " is terminating.");
    }
}