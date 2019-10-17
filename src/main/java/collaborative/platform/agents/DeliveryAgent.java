package collaborative.platform.agents;

import collaborative.platform.behaviors.DeliveryProposalBehavior;
import collaborative.platform.helper.Helper;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class DeliveryAgent extends Agent {
    @Override
    protected void setup() {

        Helper.agentPrintStarted(this);

        try {
            addBehaviour(new DeliveryProposalBehavior(this));

            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.setName(this.getAID());

            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setName(getLocalName());
            serviceDescription.setType(Protocol.SERVICE_DELIVERY);

            dfAgentDescription.addServices(serviceDescription);

            DFService.register(this, dfAgentDescription);

            Helper.agentPrintRegisteredDF(this, Protocol.SERVICE_DELIVERY);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        Helper.agentPrintStopped(this);
    }
}
