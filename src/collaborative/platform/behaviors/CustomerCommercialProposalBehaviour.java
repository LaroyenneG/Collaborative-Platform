package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CustomerCommercialProposalBehaviour extends Behaviour {

    public CustomerCommercialProposalBehaviour(CustomerAgent customerAgent) {
        super(customerAgent);
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
        System.out.println(aclMessage);
    }

    @Override
    public boolean done() {
        return false;
    }
}
