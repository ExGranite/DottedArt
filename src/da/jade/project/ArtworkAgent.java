package da.jade.project;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ArtworkAgent extends Agent {
    
    protected void setup() {
        System.out.println("Artwork Agent " + getLocalName() + " is ready.");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = myAgent.receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
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
        System.out.println("Processing artwork: " + artworkDetails);
    }

    protected void takeDown() {
        System.out.println("Artwork Agent " + getLocalName() + " is terminating.");
    }
}