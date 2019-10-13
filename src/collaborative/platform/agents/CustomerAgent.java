package collaborative.platform.agents;


import collaborative.platform.gui.CustomerGUI;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

public class CustomerAgent extends GuiAgent {

    private CustomerGUI customerGUI;

    @Override
    protected void setup() {
        customerGUI = new CustomerGUI(this);
        System.out.println("Agent " + getAID().toString() + " started");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
