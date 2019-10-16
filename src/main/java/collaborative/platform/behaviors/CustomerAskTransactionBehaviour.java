package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.model.BankTransaction;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class CustomerAskTransactionBehaviour extends OneShotBehaviour {

    private static final Random RANDOM = new SecureRandom();

    private BankTransaction bankTransaction;

    public CustomerAskTransactionBehaviour(CustomerAgent agent, BankTransaction bankTransaction) {
        super(agent);
        this.bankTransaction = bankTransaction;
    }

    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
    }

    @Override
    public void action() {

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Protocol.SERVICE_BANKER);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);

        CustomerGUI customerGUI = getCustomerAgent().getCustomerGUI();

        try {
            DFAgentDescription[] result = DFService.search(myAgent, dfAgentDescription);

            if (result.length > 0) {
                DFAgentDescription randomAgentDescription = result[RANDOM.nextInt(result.length)];
                AID aid = randomAgentDescription.getName();
                sendTransactionMessage(aid);
                customerGUI.printLog("Send bank request to : " + aid.getLocalName());
            } else {
                customerGUI.printLog("Cannot find a bank agent");
            }
        } catch (FIPAException | IOException e) {
            e.printStackTrace();
            customerGUI.printLog("Cannot send message to bank agent");
        }
    }


    private void sendTransactionMessage(AID aid) throws IOException {


        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

        message.setOntology(Protocol.ONTOLOGY);
        message.setProtocol(Protocol.BANKER_ASK_TRANSACTION);
        message.setContentObject(bankTransaction);
        message.addReceiver(aid);

        myAgent.send(message);
    }
}
