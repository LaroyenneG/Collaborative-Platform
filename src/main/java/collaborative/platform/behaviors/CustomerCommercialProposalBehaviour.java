package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.model.OrderProposal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CustomerCommercialProposalBehaviour extends Behaviour {

    private MessageTemplate messageTemplate;

    private boolean processing;

    public CustomerCommercialProposalBehaviour(CustomerAgent customerAgent) {
        super(customerAgent);
        processing = true;
        messageTemplate = MessageTemplate.and(MessageTemplate.MatchOntology(Protocol.ONTOLOGY), MessageTemplate.MatchProtocol(Protocol.CUSTOMER_OFFER));
    }

    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
    }

    @Override
    public void action() {
        ACLMessage aclMessage = myAgent.receive(messageTemplate);
        if (aclMessage != null) {
            messageProcessing(aclMessage);
        } else {
            block();
        }
    }

    private void messageProcessing(ACLMessage aclMessage) {
        try {
            OrderProposal orderProposal = (OrderProposal) aclMessage.getContentObject();
            processing = false;
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean done() {
        return !processing;
    }
}
