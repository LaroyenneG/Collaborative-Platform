package collaborative.platform.agents;

import collaborative.platform.behaviors.DeliveryProposalBehavior;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class DeliveryAgent extends Agent {
    @Override
    protected void setup() {
        try {
            addBehaviour(new DeliveryProposalBehavior(this));

            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.setName(this.getAID());

            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setName(getLocalName());
            serviceDescription.setType(Protocol.SERVICE_DELIVERY);

            dfAgentDescription.addServices(serviceDescription);

            DFService.register(this, dfAgentDescription);
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
    }
}
