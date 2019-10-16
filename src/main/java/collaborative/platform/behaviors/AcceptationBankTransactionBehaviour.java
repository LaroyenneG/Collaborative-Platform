package collaborative.platform.behaviors;

import collaborative.platform.agents.BankerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.model.BankTicket;
import collaborative.platform.model.BankTransaction;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

public class AcceptationBankTransactionBehaviour extends CyclicBehaviour {

    public AcceptationBankTransactionBehaviour(BankerAgent a) {
        super(a);
    }

    public BankerAgent myAgent() {
        return (BankerAgent) this.myAgent;
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
        if (aclMessage.getOntology().equals(Protocol.ONTOLOGY)) {

            switch (aclMessage.getProtocol()) {
                case Protocol.BANKER_ASK_TRANSACTION:
                    Long value = checkIfTransactionPossible(aclMessage);
                    AID aidReceiver = null;
                    try {
                        BankTransaction bankTransaction = (BankTransaction) aclMessage.getContentObject();
                        aidReceiver = bankTransaction.getReceiver();
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                    AID aidSender = aclMessage.getSender();
                    BankTicket bankTicket;
                    if (value!=null){
                        bankTicket = new BankTicket(aidReceiver, aidSender, value, true);
                    }else{
                        bankTicket = new BankTicket(aidReceiver, aidSender, 0, false);
                    }
                    try {
                        message.setProtocol(Protocol.TRANSACTION_REPLY);
                        message.setOntology(Protocol.ONTOLOGY);
                        message.setContentObject(bankTicket);
                        message.addReceiver(aidSender);
                        message.addReceiver(aidReceiver);
                        myAgent().send(message);
                        System.out.println("[BANKER] " + bankTicket.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private Long checkIfTransactionPossible(ACLMessage aclMessage) {
        String sender = aclMessage.getSender().getLocalName();
        BankerAgent bankerAgent = myAgent();
        Long accountSender = bankerAgent.getAccount().get(sender);
        if (accountSender == null) {
            return null;
        } else {
            try {
                BankTransaction bankTransaction = (BankTransaction) aclMessage.getContentObject();
                long value = bankTransaction.getValue();
                if (accountSender < value) {
                    System.out.println("[BANKER] " +"There is not enough fund in the account");
                    return null;
                } else {
                    String receiver = bankTransaction.getReceiver().getLocalName();
                    Long accountReceiver = bankerAgent.getAccount().get(sender);
                    if (accountReceiver == null) {
                        System.out.println("[BANKER] " +"The receiver of the transaction is unknowed by our service");
                        return null;
                    } else {
                        long newValueSender = accountSender - value;
                        long newValueReceiver = accountReceiver + value;
                        bankerAgent.getAccount().put(sender, newValueSender);
                        bankerAgent.getAccount().put(receiver, newValueReceiver);
                        System.out.println("[BANKER] " +"The transaction is effectued :" +value);
                        return value;
                    }
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
                return null;
            }

        }

    }
}

