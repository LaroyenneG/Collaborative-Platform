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
                    g.drawImage(ImageIO.read(new URL("https://media.licdn.com/dms/image/C4D03AQEPcYWTlHKSSA/profile-displayphoto-shrink_800_800/0?e=1576713600&v=beta&t=-19dMEZXWhxKvhVgtN4mlQW2bNmD92xK71H7J9B7rBM")), 0, 0, null);
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
