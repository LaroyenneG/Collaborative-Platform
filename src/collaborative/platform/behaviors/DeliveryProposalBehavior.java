package collaborative.platform.behaviors;

import collaborative.platform.agents.Protocol;
import collaborative.platform.model.DeliveryProposal;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class DeliveryProposalBehavior extends CyclicBehaviour {
    public DeliveryProposalBehavior(Agent a) {
        super(a);
    }

    @Override
    public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            messageProcessing(aclMessage);
        } else {
            block();
        }
    }

    private void messageProcessing(ACLMessage aclMessage) {
        switch (aclMessage.getProtocol()) {
            case Protocol.DELIVERY_REQUEST_PRICE:
                // je dois envoyer BUYER_OFFER_FROM_DELIVERY
                processRequestPrice(aclMessage);
                break;
            default:
                throw new RuntimeException("delivery doesn't understand protocol"); // todo utilitée ?
        }
    }

    private void processRequestPrice(ACLMessage aclMessage) {
        DeliveryProposal deliveryProposal = new DeliveryProposal(
                System.currentTimeMillis(),
                ThreadLocalRandom.current().nextInt(1, 1001)
        );

        try {
            ACLMessage aclReply = aclMessage.createReply();
            aclReply.setContentObject(deliveryProposal);
            myAgent.send(aclReply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
