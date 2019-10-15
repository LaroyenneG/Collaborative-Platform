package collaborative.platform.behaviors;

import collaborative.platform.agents.Protocol;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AcceptationBankTransactionBehaviour extends CyclicBehaviour {
    public AcceptationBankTransactionBehaviour(Agent a) {
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
            case Protocol.BANKER_ASK_TRANSACTION:
                checkIfTransactionPossible(aclMessage);
                break;
        }
    }

    private void checkIfTransactionPossible(ACLMessage aclMessage) {

    }
}

