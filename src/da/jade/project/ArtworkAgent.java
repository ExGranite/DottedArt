package da.jade.project;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ArtworkAgent extends Agent {
    
    protected void setup() {
        System.out.println("Artwork Agent " + getLocalName() + " is ready.");

        // Add a behavior to handle artwork uploads and requests
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = myAgent.receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    // Process the artwork upload or request
                    processArtwork(content);
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Artwork processed successfully.");
                    send(reply);
                } else {
                    block();
                }
            }
        });
    }

    private void processArtwork(String artworkDetails) {
        // Simulate artwork processing logic
        System.out.println("Processing artwork: " + artworkDetails);
    }

    protected void takeDown() {
        System.out.println("Artwork Agent " + getLocalName() + " is terminating.");
    }
}