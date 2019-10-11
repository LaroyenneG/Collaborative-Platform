package collaborative.platform;

import collaborative.platform.agents.BuyerAgent;
import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.agents.DeliveryAgent;
import collaborative.platform.agents.SellerAgent;
import collaborative.platform.containers.Container;
import collaborative.platform.containers.MainContainer;
import collaborative.platform.containers.SimpleContainer;
import jade.wrapper.ControllerException;

import java.util.*;

public class Application {

    private static final String DEFAULT_HOSTNAME = "hostname";
    private static final String MAIN_CONTAINER_NAME = "Main";
    private static final String CUSTOMER_CONTAINER_NAME = "Customer";
    private static final String BANK_CONTAINER_NAME = "Bank";

    private static final String OPTION_FLAG = "--container";

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
        System.err.println("java " + Application.class.getSimpleName() + " [" + OPTION_FLAG + " <name> [hostname]]...");
        System.exit(-1);
    }


    private static Container createContainer(List<String> containerParams) {

        Container container = null;

        if (!containerParams.isEmpty() && containerParams.size() <= 2) {

            String name = containerParams.get(0);
            String hostname = (containerParams.size() >= 2) ? containerParams.get(1) : DEFAULT_HOSTNAME;


            switch (name) {

                case MAIN_CONTAINER_NAME:
                    if (!hostname.equals(DEFAULT_HOSTNAME)) {
                        System.err.println("Main container must bind localhost !");
                    }
                    container = new MainContainer(mainContainerAgents);
                    break;

                case CUSTOMER_CONTAINER_NAME:
                    container = new SimpleContainer(hostname, customerContainerAgents);
                    break;

                case BANK_CONTAINER_NAME:
                    container = new SimpleContainer(hostname, bankContainerAgents);
                    break;

                default:
                    System.err.println("Invalid container name : " + name);
            }
        }

        return container;
    }

    public static void main(String[] args) {

        List<Container> containerList = new ArrayList<>();

        if (args.length == 0) {

            containerList.add(createContainer(Collections.singletonList(MAIN_CONTAINER_NAME)));
            containerList.add(createContainer(Arrays.asList(CUSTOMER_CONTAINER_NAME, DEFAULT_HOSTNAME)));
            containerList.add(createContainer(Arrays.asList(BANK_CONTAINER_NAME, DEFAULT_HOSTNAME)));

        } else {

            int argsCursor = 0;

            while (argsCursor < args.length) {

                if (args[argsCursor].equals(OPTION_FLAG)) {

                    argsCursor++;

                    List<String> containerParams = new ArrayList<>();

                    while (argsCursor < args.length && !args[argsCursor].equals(OPTION_FLAG)) {

                        containerParams.add(args[argsCursor]);

                        argsCursor++;
                    }

                    Container container = createContainer(containerParams);

                    if (container == null) {
                        usage();
                    }

                    containerList.add(container);

                } else {
                    usage();
                }

                argsCursor++;
            }
        }

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
