package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.model.Product;
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

public class CustomerBuyProductBehavior extends OneShotBehaviour {

    private static final Random RANDOM = new SecureRandom();

    private Product product;

    public CustomerBuyProductBehavior(CustomerAgent agent, Product product) {
        super(agent);
        this.product = product;
    }

    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
    }

    @Override
    public void action() {

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Protocol.SERVICE_BUYER);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);

        CustomerGUI customerGUI = getCustomerAgent().getCustomerGUI();

        try {

            DFAgentDescription[] result = DFService.search(myAgent, dfAgentDescription);

            if (result.length > 0) {
                DFAgentDescription randomAgentDescription = result[RANDOM.nextInt(result.length)];
                AID aid = randomAgentDescription.getName();
                sendBuyerBuyMessage(aid);
                customerGUI.printLog("Send price request to : " + aid.getLocalName());
            } else {
                customerGUI.printLog("Cannot find a buyer agent");
            }

        } catch (FIPAException | IOException e) {
            e.printStackTrace();
            customerGUI.printLog("Cannot send a message to buyer agent");
        }
    }

    private void sendBuyerBuyMessage(AID aid) throws IOException {

        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

        message.setOntology(Protocol.ONTOLOGY);
        message.setProtocol(Protocol.BUYER_BUY);
        message.setContentObject(product);
        message.addReceiver(aid);

        myAgent.send(message);
    }
}
