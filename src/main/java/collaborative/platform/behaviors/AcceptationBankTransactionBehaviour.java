package collaborative.platform.behaviors;

import collaborative.platform.agents.BankerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.model.BankTicket;
import collaborative.platform.model.BankTransaction;
import com.sun.tools.javac.util.Pair;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

import static javax.sound.midi.MidiSystem.getReceiver;

public class AcceptationBankTransactionBehaviour extends CyclicBehaviour {
    public AcceptationBankTransactionBehaviour(BankerAgent a) {
        super(a);
    }


    public BankerAgent myAgent(){
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
        if (aclMessage.getOntology().equals(Protocol.ONTOLOGY)){

        switch (aclMessage.getProtocol()){
            case Protocol.BANKER_ASK_TRANSACTION:
                Pair<Boolean, Long> possible = checkIfTransactionPossible(aclMessage);
                AID aidReceiver = null;
                try {
                    BankTransaction bankTransaction = (BankTransaction) aclMessage.getContentObject();
                    aidReceiver = bankTransaction.getReceiver();
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                AID aidSender = aclMessage.getSender();
                BankTicket bt = new BankTicket(aidReceiver,aidSender,possible.snd,possible.fst);
                try {
                    message.setProtocol(Protocol.TRANSACTION_REPLY);
                    message.setOntology(Protocol.ONTOLOGY);
                    message.setContentObject(bt);
                    message.addReceiver(aidSender);
                    message.addReceiver(aidReceiver);
                    myAgent().send(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }

        }
    }

    private Pair<Boolean,Long> checkIfTransactionPossible(ACLMessage aclMessage) {
        String sender = aclMessage.getSender().getLocalName();
        BankerAgent bankerAgent = myAgent();
        Long accountSender = bankerAgent.getAccount().get(sender);
        //Pair<Boolean,Long> pair = null;
        if (accountSender == null){
            return new Pair(false,0);
        } else {
            try {
                BankTransaction bankTransaction = (BankTransaction) aclMessage.getContentObject();
                long value = bankTransaction.getValue();
                if (accountSender < value){
                    return new Pair(false,0);
                }else{
                    String receiver = bankTransaction.getReceiver().getLocalName();
                    Long accountReceiver = bankerAgent.getAccount().get(sender);
                    if (accountReceiver == null){
                        return new Pair(false,0);
                    }else {
                        long newValueSender = accountSender - value;
                        long newValueReceiver = accountReceiver + value;
                        bankerAgent.getAccount().put(sender,newValueSender);
                        bankerAgent.getAccount().put(receiver,newValueReceiver);
                        return new Pair(true,value);
                    }
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
                return new Pair(false,0);
            }

        }


//TODO System.out.println pour voir les trucs qui se passe
        //TODO nettoyer Pair
    }
}

