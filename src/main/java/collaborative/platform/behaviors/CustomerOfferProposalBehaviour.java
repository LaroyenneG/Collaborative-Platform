package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.model.OrderProposal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CustomerOfferProposalBehaviour extends Behaviour {

    public static final MessageTemplate MESSAGE_TEMPLATE = MessageTemplate.and(MessageTemplate.MatchOntology(Protocol.ONTOLOGY), MessageTemplate.MatchProtocol(Protocol.CUSTOMER_OFFER));

    private boolean processing;

    public CustomerOfferProposalBehaviour(CustomerAgent customerAgent) {
        super(customerAgent);
        processing = true;
    }

    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
    }

    @Override
    public void action() {
        ACLMessage aclMessage = myAgent.receive(MESSAGE_TEMPLATE);
        if (aclMessage != null) {
            messageProcessing(aclMessage);
        } else {
            block();
        }
    }

    private void messageProcessing(ACLMessage aclMessage) {
        try {
            OrderProposal orderProposal = (OrderProposal) aclMessage.getContentObject();

            CustomerGUI customerGUI = getCustomerAgent().getCustomerGUI();
            customerGUI.setOrderProposal(orderProposal);
            customerGUI.unlockDecisionButtons();

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
