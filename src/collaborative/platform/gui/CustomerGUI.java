package collaborative.platform.gui;

import collaborative.platform.agents.CustomerAgent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CustomerGUI extends JFrame {

    private CustomerAgent customerAgent;

    public CustomerGUI(CustomerAgent customerAgent) {
        this.customerAgent = customerAgent;
        initComponent();
    }

    private void initComponent() {
        setTitle(customerAgent.getLocalName());
        setSize(500, 500);
        customerAgent.postGuiEvent(null);

        setContentPane(new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new URL("http://lorempixel.com/400/200/nature/")), 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CustomerAgent getCustomerAgent() {
        return customerAgent;
    }
}
