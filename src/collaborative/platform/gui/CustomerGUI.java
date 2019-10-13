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
        setTitle(customerAgent.getName());
        setSize(300, 300);
        setVisible(true);
    }

    public CustomerAgent getCustomerAgent() {
        return customerAgent;
    }
}
