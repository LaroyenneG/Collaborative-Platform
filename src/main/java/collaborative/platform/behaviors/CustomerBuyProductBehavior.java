package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.Protocol;
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
        dfAgentDescription.addOntologies(Protocol.ONTOLOGY);

        try {
            DFAgentDescription[] result = DFService.search(myAgent, dfAgentDescription);

            if (result.length > 0) {
                DFAgentDescription randomAgentDescription = result[RANDOM.nextInt(result.length)];
                AID aid = randomAgentDescription.getName();
                sendBuyerBuyMessage(aid);
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    private void sendBuyerBuyMessage(AID aid) {

        try {
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

            message.setOntology(Protocol.ONTOLOGY);
            message.setProtocol(Protocol.BUYER_BUY);
            message.setContentObject(product);
            message.addReceiver(aid);

            myAgent.send(message);

            getCustomerAgent().getCustomerGUI().printLog("Send request to : " + aid.getLocalName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
