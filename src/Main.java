import jade.core.Agent;
import jade.core.behaviours.BaseInitiator;
import jade.lang.acl.ACLMessage;

public class Main {

    public static void main(String[] args) {

        Agent agent = new Agent();

        agent.addBehaviour(new BaseInitiator() {
            @Override
            protected ACLMessage createInitiation() {
                return null;
            }
        });

    }
}
