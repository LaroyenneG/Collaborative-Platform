package collaborative.platform.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;

import java.util.Map;

public class MainContainer extends Container {

    public MainContainer(Map<String, String> agents) {
        super(agents);
    }

    @Override
    protected AgentContainer createAgentContainer() {

        Runtime runtime = Runtime.instance();

        Properties properties = new ExtendedProperties();

        properties.setProperty(Profile.GUI, "true");

        ProfileImpl profile = new ProfileImpl(properties);

        return runtime.createMainContainer(profile);
    }
}
