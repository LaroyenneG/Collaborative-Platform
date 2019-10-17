package collaborative.platform.agents;

import collaborative.platform.behaviors.SellerBehaviour;
import collaborative.platform.helper.Helper;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class SellerAgent extends Agent {
    @Override
    protected void setup() {

        Helper.agentPrintStarted(this);

        try {
            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.setName(this.getAID());

            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setName(getLocalName());
            serviceDescription.setType(Protocol.SERVICE_SELLER);

            dfAgentDescription.addServices(serviceDescription);

            DFService.register(this, dfAgentDescription);

            Helper.agentPrintRegisteredDF(this, Protocol.SERVICE_SELLER);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        addBehaviour(new SellerBehaviour(this));
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Helper.agentPrintStopped(this);
    }
}
