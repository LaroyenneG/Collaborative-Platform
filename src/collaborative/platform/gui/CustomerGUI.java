package collaborative.platform.gui;

import collaborative.platform.agents.CustomerAgent;

import javax.swing.*;

public class CustomerGUI extends JFrame {

    private CustomerAgent customerAgent;

    public CustomerGUI(CustomerAgent customerAgent) {
        this.customerAgent = customerAgent;
        initComponent();
    }

    private void initComponent() {
        setTitle(customerAgent.getLocalName());
        setSize(300, 300);
        customerAgent.postGuiEvent(null);
    }

    public CustomerAgent getCustomerAgent() {
        return customerAgent;
    }
}
