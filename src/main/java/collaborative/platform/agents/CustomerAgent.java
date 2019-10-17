package collaborative.platform.agents;


import collaborative.platform.behaviors.*;
import collaborative.platform.gui.CustomerGUI;
import collaborative.platform.helper.Helper;
import collaborative.platform.model.BankTransaction;
import collaborative.platform.model.OrderProposal;
import collaborative.platform.model.Product;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CustomerAgent extends GuiAgent {

    private transient CustomerGUI customerGUI;

    private Product soughtProduct;
    private OrderProposal orderProposal;

    @Override
    protected void setup() {

        Helper.agentPrintStarted(this);

        initAttributes();
        initGUI();
        customerGUI.printLog("Agent started !");
        addBehaviour(new CustomerOfferBehaviour(this));
        addBehaviour(new CustomerTrashMessageBehaviour(this));
        addBehaviour(new CustomerTransactionReplyBehaviour(this));
    }

    private void initAttributes() {
        customerGUI = null;
        soughtProduct = null;
        orderProposal = null;
    }

    @Override
    protected void takeDown() {
        customerGUI.printLog("Agent stopped !");
        stopGUI();
        Helper.agentPrintStopped(this);
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
                addBehaviour(new CustomerBuyProductBehavior(this, soughtProduct));
                break;

            case CustomerGUI.ACCEPT_FRAME_CODE:
                BankTransaction bankTransaction = new BankTransaction(orderProposal.getFrom(), orderProposal.getPrice());
                addBehaviour(new CustomerAskTransactionBehaviour(this, bankTransaction));
                break;

            case CustomerGUI.REFUSE_FRAME_CODE:
                orderProposal = null;
                soughtProduct = null;
                break;

            case CustomerGUI.PRODUCT_SELECTED_FRAME_CODE:
                soughtProduct = (Product) guiEvent.getParameter(0);
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
                customerGUI.actualise();
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

    public Product getSoughtProduct() {
        return soughtProduct;
    }

    public OrderProposal getOrderProposal() {
        return orderProposal;
    }

    public void setOrderProposal(OrderProposal orderProposal) {
        this.orderProposal = orderProposal;
    }

    public CustomerGUI getCustomerGUI() {
        return customerGUI;
    }
}
