package collaborative.platform.behaviors;

import collaborative.platform.agents.Protocol;
import collaborative.platform.model.CommercialProposal;
import collaborative.platform.model.Product;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

public class BuyerBuyProductBehaviour extends CyclicBehaviour {

    public BuyerBuyProductBehaviour(Agent a) {
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
        switch (aclMessage.getProtocol()){
            case Protocol.BUYER_BUY:
                processBuy(aclMessage);
                break;
        }
    }

    private void processBuy(ACLMessage aclMessage){

        // Search all sellers
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Protocol.SERVICE_SELLER);

        DFAgentDescription sellers = new DFAgentDescription();
        sellers.addServices(sd);

        try {
            Product product = (Product) aclMessage.getContentObject();

            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
            message.setContentObject(product);
            message.setProtocol(Protocol.SELLER_REQUEST_PRICE);

            // Send the message to all sellers
            DFAgentDescription[] result = DFService.search(myAgent, sellers);
            for(DFAgentDescription agent : result){
                message.addReceiver(agent.getName());
            }

            myAgent.send(message);
        } catch (UnreadableException | IOException | FIPAException e) {
            e.printStackTrace();
        }
    }
}
