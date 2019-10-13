package collaborative.platform.agents;


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
        System.out.println("Agent " + getAID().toString() + " started");
    }

    @Override
    protected void beforeMove() {
        customerGUI.setVisible(false);
        customerGUI.dispose();
        customerGUI = null;
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

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
