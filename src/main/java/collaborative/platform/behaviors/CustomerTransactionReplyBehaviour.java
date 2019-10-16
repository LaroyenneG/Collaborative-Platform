package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.model.BankTicket;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CustomerTransactionReplyBehaviour extends CyclicBehaviour {

    public static final MessageTemplate MESSAGE_TEMPLATE = MessageTemplate.and(MessageTemplate.MatchOntology(Protocol.ONTOLOGY), MessageTemplate.MatchProtocol(Protocol.TRANSACTION_REPLY));

    public CustomerTransactionReplyBehaviour(CustomerAgent customerAgent) {
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
            BankTicket bankTicket = (BankTicket) aclMessage.getContentObject();
            customerGUI.informBankTicket(bankTicket);
            customerGUI.actualise();
            customerGUI.printLog("Bank ticket :\n" + bankTicket.toString());
        } catch (UnreadableException e) {
            e.printStackTrace();
            customerGUI.printLog("Bank ticket error");
        }
    }
}
