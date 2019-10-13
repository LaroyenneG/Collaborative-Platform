package collaborative.platform.agents;


import collaborative.platform.gui.CustomerGUI;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CustomerAgent extends GuiAgent {

    private transient CustomerGUI customerGUI;


    private void createGUI() {
        customerGUI = new CustomerGUI(this);
    }

    @Override
    protected void setup() {

        try {
            SwingUtilities.invokeAndWait(this::createGUI);
        } catch (InterruptedException | InvocationTargetException e) {
            customerGUI = null;
            e.printStackTrace();
        }

        System.out.println("Agent " + getAID().toString() + " started");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
