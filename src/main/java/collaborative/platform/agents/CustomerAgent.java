package collaborative.platform.agents;


import collaborative.platform.behaviors.CustomerAskTransactionBehaviour;
import collaborative.platform.behaviors.CustomerBuyProductBehavior;
import collaborative.platform.behaviors.CustomerOfferProposalBehaviour;
import collaborative.platform.behaviors.CustomerTrashMessageBehaviour;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.model.BankTransaction;
import collaborative.platform.model.Product;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CustomerAgent extends GuiAgent {

    private transient CustomerGUI customerGUI;

    @Override
    protected void setup() {
        initGUI();
        customerGUI.printLog("Agent AID : \n" + getAID().toString() + "\n agent started");
        addBehaviour(new CustomerOfferProposalBehaviour(this));
        addBehaviour(new CustomerTrashMessageBehaviour(this));
    }

    @Override
    protected void takeDown() {
        customerGUI.printLog("Agent " + getAID().toString() + " end");
        stopGUI();
    }

    @Override
    protected void beforeMove() {
        stopGUI();
    }

    @Override
    protected void afterMove() {
        initGUI();
    }

    @Override
    protected void beforeClone() {

    }

    @Override
    protected void afterClone() {
        initGUI();
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

        switch (guiEvent.getType()) {

            case CustomerGUI.EXIT_FRAME_CODE:
                doDelete();
                break;

            case CustomerGUI.STOP_FRAME_CODE:
                doDelete();
                break;

            case CustomerGUI.BUY_FRAME_CODE:
                Product product = (Product) guiEvent.getParameter(0);
                addBehaviour(new CustomerBuyProductBehavior(this, product));
                break;

            case CustomerGUI.ACCEPT_FRAME_CODE:
                BankTransaction bankTransaction = (BankTransaction) guiEvent.getParameter(0);
                addBehaviour(new CustomerAskTransactionBehaviour(this, bankTransaction));
                break;

            default:
                throw new IllegalStateException("Invalid GUI event");
        }
    }

    private void initGUI() {
        try {
            final CustomerAgent agent = this;

            SwingUtilities.invokeAndWait(() -> {
                customerGUI = new CustomerGUI(agent);
                customerGUI.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            customerGUI = null;
            e.printStackTrace();
        }
    }

    private void stopGUI() {
        customerGUI.setVisible(false);
        customerGUI.dispose();
        customerGUI = null;
    }

    public CustomerGUI getCustomerGUI() {
        return customerGUI;
    }
}
