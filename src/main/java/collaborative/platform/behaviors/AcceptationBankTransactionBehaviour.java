package collaborative.platform.behaviors;

import collaborative.platform.agents.BankerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.model.BankTransaction;
import com.sun.tools.javac.util.Pair;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

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
        switch (aclMessage.getProtocol()){
            case Protocol.BANKER_ASK_TRANSACTION:
                boolean possible = checkIfTransactionPossible(aclMessage);
                /*new
                ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
                message.setContentObject(possible);
                message.setProtocol(Protocol.);
*/
                break;
        }
    }

    private Pair<Boolean,Long> checkIfTransactionPossible(ACLMessage aclMessage) {
        String sender = aclMessage.getSender().getLocalName();
        BankerAgent bankerAgent = myAgent();
        Long accountSender = bankerAgent.getAccount().get(sender);
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
                        Long newValueSender = accountSender - value;
                        Long newValueReceiver = accountReceiver + value;
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



    }
}

