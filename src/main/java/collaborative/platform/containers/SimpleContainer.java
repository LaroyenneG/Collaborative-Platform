package collaborative.platform.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

import java.util.Map;

public class SimpleContainer extends Container {

    private String address;

    public SimpleContainer(String address, Map<String, String> agents) {
        super(agents);
        this.address = address;
    }

    @Override
    protected AgentContainer createAgentContainer() {

        Runtime runtime = Runtime.instance();

        ProfileImpl profile = new ProfileImpl(false);

        profile.setParameter(Profile.MAIN_HOST, address);

        return runtime.createAgentContainer(profile);
    }
}
