package collaborative.platform.agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class BankerAgent extends Agent {
    protected void setup() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(this.getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName(getLocalName());
        serviceDescription.setType(Protocol.SERVICE_BANKER);
    }

    @Override
    protected void takeDown() {
        try{
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

    }

}
