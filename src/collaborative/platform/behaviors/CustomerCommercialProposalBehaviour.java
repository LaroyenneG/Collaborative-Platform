package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.model.OrderProposal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class CustomerCommercialProposalBehaviour extends Behaviour {

    private boolean processing;

    public CustomerCommercialProposalBehaviour(CustomerAgent customerAgent) {
        super(customerAgent);
        processing = true;
    }

    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
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

        if (aclMessage.getOntology().equals(Protocol.ONTOLOGY) && aclMessage.getProtocol().equals(Protocol.CUSTOMER_OFFER)) {

            try {
                OrderProposal orderProposal = (OrderProposal) aclMessage.getContentObject();
                processing = false;
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }

        System.out.println(aclMessage);
    }

    @Override
    public boolean done() {
        return !processing;
    }
}
