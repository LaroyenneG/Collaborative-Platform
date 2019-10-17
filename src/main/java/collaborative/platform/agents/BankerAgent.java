package collaborative.platform.agents;

import collaborative.platform.behaviors.AcceptationBankTransactionBehaviour;
import collaborative.platform.helper.Helper;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.HashMap;
import java.util.Map;

public class BankerAgent extends Agent {

    private Map<String,Long> account;

    protected void setup() {

        Helper.agentPrintStarted(this);

        account = new HashMap<>();
        account.put("Customer-A", 100L);
        account.put("Customer-B", 10L);
        account.put("Customer-C", 5L);

        try {
            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.setName(this.getAID());

            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setName(getLocalName());
            serviceDescription.setType(Protocol.SERVICE_BANKER);

            dfAgentDescription.addServices(serviceDescription);

            DFService.register(this, dfAgentDescription);

            Helper.agentPrintRegisteredDF(this, Protocol.SERVICE_BANKER);
        } catch (FIPAException e1) {
            e1.printStackTrace();
        }

        addBehaviour(new AcceptationBankTransactionBehaviour(this));
    }

    @Override
    protected void takeDown() {
        try{
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        Helper.agentPrintStopped(this);
    }

    public Map<String, Long> getAccount(){
        return account;
    }

}
