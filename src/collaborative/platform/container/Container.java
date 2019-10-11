package collaborative.platform.container;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Container {

    private Map<String, String> agents;
    private Set<AgentController> agentControllers;
    private AgentContainer agentContainer;


    public Container(Map<String, String> agents) {
        this.agents = agents;
        agentContainer = createAgentContainer();
        agentControllers = new HashSet<>();
    }


    public void createAgents() throws StaleProxyException {

        final Object[] arguments = new Object[0];

        for (String key : agents.keySet()) {
            agentControllers.add(agentContainer.createNewAgent(key, agents.get(key), arguments));
        }
    }

    protected abstract AgentContainer createAgentContainer();


    public void start() throws ControllerException {

        agentContainer.start();

        for (AgentController agentController : agentControllers) {
            agentController.start();
        }
    }
}
