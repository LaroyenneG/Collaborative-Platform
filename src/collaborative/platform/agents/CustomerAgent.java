package collaborative.platform.agents;


import collaborative.platform.behaviors.CustomerCommercialProposalBehaviour;
import collaborative.platform.gui.CustomerGUI;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CustomerAgent extends GuiAgent {

    private transient CustomerGUI customerGUI;

    @Override
    protected void setup() {
        initGUI();
        addBehaviour(new CustomerCommercialProposalBehaviour(this));
        System.out.println("Agent " + getAID().toString() + " started");
    }

    @Override
    protected void takeDown() {
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
}
