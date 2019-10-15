package collaborative.platform.behaviors;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.model.BankTransaction;
import jade.core.behaviours.OneShotBehaviour;

public class CustomerAskTransactionBehaviour extends OneShotBehaviour {

    private BankTransaction bankTransaction;

    public CustomerAskTransactionBehaviour(CustomerAgent agent, BankTransaction bankTransaction) {
        super(agent);
        this.bankTransaction = bankTransaction;
    }

    public CustomerAgent getCustomerAgent() {
        return (CustomerAgent) myAgent;
    }

    @Override
    public void action() {


    }
}
