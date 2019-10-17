package collaborative.platform.helper;


import jade.core.Agent;

public class Helper {


    private static String printLogHeader(Agent agent) {

        return '[' +
                agent.getLocalName() +
                ']' +
                ' ';
    }

    public static void agentPrint(Agent agent, String msg) {
        System.out.println(printLogHeader(agent) + msg);
    }

    public static void agentPrintStarted(Agent agent) {
        System.out.println(printLogHeader(agent) + "started");
    }

    public static void agentPrintStopped(Agent agent) {
        System.out.println(printLogHeader(agent) + "stopped");
    }

    public static void agentPrintRegisteredDF(Agent agent, String service) {
        System.out.println(printLogHeader(agent) + "registered service : " + service);
    }
}
