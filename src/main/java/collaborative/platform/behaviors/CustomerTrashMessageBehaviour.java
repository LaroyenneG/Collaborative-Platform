package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CustomerTrashMessageBehaviour extends CyclicBehaviour {

    public static final MessageTemplate MESSAGE_TEMPLATE = MessageTemplate.not(MessageTemplate.and(CustomerOfferBehaviour.MESSAGE_TEMPLATE, CustomerTransactionReplyBehaviour.MESSAGE_TEMPLATE));

    public CustomerTrashMessageBehaviour(CustomerAgent customerAgent) {
        super(customerAgent);
    }


    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
    }

    @Override
    public void action() {

        ACLMessage aclMessage = myAgent.receive(MESSAGE_TEMPLATE);
        if (aclMessage != null) {
            getCustomerAgent().getCustomerGUI().printLog(
                    "------------------------------\n" +
                            "      Attention !!\n"
                            + "-------------------------------\n" +
                            "Invalid message (no template matching) : \n" + aclMessage.toString());
        } else {
            block();
        }
    }
}
