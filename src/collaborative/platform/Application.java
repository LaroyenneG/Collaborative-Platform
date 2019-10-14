package collaborative.platform;

import collaborative.platform.agents.BuyerAgent;
import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.DeliveryAgent;
import collaborative.platform.agents.SellerAgent;
import collaborative.platform.containers.Container;
import collaborative.platform.containers.MainContainer;
import collaborative.platform.containers.SimpleContainer;
import jade.wrapper.ControllerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private static final String DEFAULT_HOSTNAME = "localhost";

    private static final Map<String, String> mainContainerAgents = new HashMap<>();
    private static final Map<String, String> bankContainerAgents = new HashMap<>();
    private static final Map<String, String> customerContainerAgents = new HashMap<>();

    static {

        /* Customer container Agents */
        customerContainerAgents.put("Customer-A", CustomerAgent.class.getName());
        customerContainerAgents.put("Customer-B", CustomerAgent.class.getName());
        customerContainerAgents.put("Customer-C", CustomerAgent.class.getName());


        /* Main container Agents */
        mainContainerAgents.put("Buyer-A", BuyerAgent.class.getName());
        mainContainerAgents.put("Buyer-B", BuyerAgent.class.getName());
        mainContainerAgents.put("Buyer-C", BuyerAgent.class.getName());

        mainContainerAgents.put("Seller-A", SellerAgent.class.getName());
        mainContainerAgents.put("Seller-B", SellerAgent.class.getName());
        mainContainerAgents.put("Seller-C", SellerAgent.class.getName());

        mainContainerAgents.put("Delivery-A", DeliveryAgent.class.getName());
        mainContainerAgents.put("Delivery-B", DeliveryAgent.class.getName());
        mainContainerAgents.put("Delivery-C", DeliveryAgent.class.getName());


        /* Bank container Agents */
        bankContainerAgents.put("Banker-A", BuyerAgent.class.getName());
        bankContainerAgents.put("Banker-B", BuyerAgent.class.getName());
        bankContainerAgents.put("Banker-C", BuyerAgent.class.getName());
    }

    private static void usage() {
        System.err.println("java " + Application.class.getSimpleName());
        System.exit(-1);
    }

    public static void main(String[] args) {

        if (args.length != 0) {
            usage();
        }

        List<Container> containerList = new ArrayList<>();

        containerList.add(new MainContainer(mainContainerAgents));
        containerList.add(new SimpleContainer(DEFAULT_HOSTNAME, customerContainerAgents));
        containerList.add(new SimpleContainer(DEFAULT_HOSTNAME, bankContainerAgents));

        try {
            for (Container container : containerList) {
                container.createAgents();
                container.start();
            }
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}
