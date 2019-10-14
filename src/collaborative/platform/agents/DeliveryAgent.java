package collaborative.platform.agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class DeliveryAgent extends Agent {
    @Override
    protected void setup() {
        try {
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
}
