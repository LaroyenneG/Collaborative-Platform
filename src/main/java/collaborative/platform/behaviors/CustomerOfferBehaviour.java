package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.model.OrderProposal;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CustomerOfferBehaviour extends CyclicBehaviour {

    public static final MessageTemplate MESSAGE_TEMPLATE = MessageTemplate.and(MessageTemplate.MatchOntology(Protocol.ONTOLOGY), MessageTemplate.MatchProtocol(Protocol.CUSTOMER_OFFER));

    public CustomerOfferBehaviour(CustomerAgent customerAgent) {
        super(customerAgent);
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

        CustomerAgent customerAgent = getCustomerAgent();
        CustomerGUI customerGUI = customerAgent.getCustomerGUI();

        try {
            OrderProposal orderProposal = (OrderProposal) aclMessage.getContentObject();
            orderProposal.setFrom(aclMessage.getSender());
            customerAgent.setOrderProposal(orderProposal);
            customerGUI.actualise();
        } catch (UnreadableException e) {
            e.printStackTrace();
            customerGUI.printLog("Order proposal error");
        }
    }
}
