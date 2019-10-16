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
        if (Protocol.DELIVERY_REQUEST_PRICE.equals(aclMessage.getProtocol())) {
            processRequestPrice(aclMessage);
        }
    }

    private void processRequestPrice(ACLMessage aclMessage) {
        System.out.println("[" + this.myAgent.getLocalName() + "] Request Price received.");
        DeliveryProposal deliveryProposal = new DeliveryProposal(
                System.currentTimeMillis(),
                ThreadLocalRandom.current().nextInt(1, 1001)
        );

        try {
            ACLMessage aclReply = aclMessage.createReply();
            aclReply.setProtocol(Protocol.BUYER_OFFER_FROM_DELIVERY);
            aclReply.setContentObject(deliveryProposal);
            myAgent.send(aclReply);
            System.out.println("[" + this.myAgent.getLocalName() + "] Offer send to buyer.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
