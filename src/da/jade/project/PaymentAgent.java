package da.jade.project;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class PaymentAgent extends Agent {
    private double transactionAmount;

    protected void setup() {
        System.out.println("Payment Agent " + getLocalName() + " is ready.");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = myAgent.receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    if (processPayment(content)) {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent("Payment successful for amount: " + transactionAmount);
                        send(reply);
                    } else {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.FAILURE);
                        reply.setContent("Payment failed: Invalid card details.");
                        send(reply);
                    }
                } else {
                    block();
                }
            }
        });
    }

    private boolean processPayment(String paymentDetails) {
        String[] details = paymentDetails.split(",");
        if (details.length == 4) {
            transactionAmount = 100.00;
            System.out.println("Processing payment of $" + transactionAmount + " for card " + details[0]);
            return true;
        }
        return false;
    }

    protected void takeDown() {
        System.out.println("Payment Agent " + getLocalName() + " is terminating.");
    }
}