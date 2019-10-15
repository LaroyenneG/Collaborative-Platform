package collaborative.platform.gui;

import collaborative.platform.agents.CustomerAgent;
import collaborative.platform.model.OrderProposal;
import collaborative.platform.model.Product;
import jade.gui.GuiEvent;
import jade.wrapper.ControllerException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomerGUI extends JFrame {

    public static final int EXIT_FRAME_CODE = 1;
    public static final int STOP_FRAME_CODE = 2;
    public static final int BUY_FRAME_CODE = 3;
    public static final int ACCEPT_FRAME_CODE = 4;

    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel agentName;
    private javax.swing.JLabel agentNameLabel;
    private javax.swing.JButton infoButton;
    private javax.swing.JLabel journalLabel;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel priceValue;
    private javax.swing.JComboBox<String> productsComboBox;
    private javax.swing.JButton refuseButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel selectLabel;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel titleLabel;

    private CustomerAgent customerAgent;

    private Map<String, Product> stringProductMap;

    public CustomerGUI(CustomerAgent customerAgent) {
        this.customerAgent = customerAgent;
        this.stringProductMap = new HashMap<>();
        initComponents();
        initProductItems();
        initAgentLabel();
        lockDecisionButtons();
    }

    private void initAgentLabel() {
        try {
            String simpleContainerName = customerAgent.getContainerController().getContainerName();
            String simpleAgentName = customerAgent.getLocalName();

            agentName.setText(simpleAgentName + " in " + simpleContainerName);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void initProductItems() {

        Set<Product> productSet = Product.productList();

        for (Product product : productSet) {
            stringProductMap.put(product.toString(), product);
        }

        productsComboBox.removeAllItems();

        for (String key : stringProductMap.keySet()) {
            productsComboBox.addItem(key);
        }

        if (productsComboBox.getItemCount() > 0) {
            productsComboBox.setSelectedIndex(1);
        }
    }

    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        agentNameLabel = new javax.swing.JLabel();
        agentName = new javax.swing.JLabel();
        selectLabel = new javax.swing.JLabel();
        productsComboBox = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        acceptButton = new javax.swing.JButton();
        priceLabel = new javax.swing.JLabel();
        priceValue = new javax.swing.JLabel();
        refuseButton = new javax.swing.JButton();
        logScrollPane = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        journalLabel = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(0, 204, 0));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Collaborative Platform");

        agentNameLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        agentNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        agentNameLabel.setText("Agent :");

        agentName.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        agentName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        selectLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        selectLabel.setText("Please select a product :");

        searchButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        searchButton.setText("Search best price");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        acceptButton.setForeground(new java.awt.Color(0, 102, 0));
        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        priceLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceLabel.setText("Best price :");

        priceValue.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        priceValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        refuseButton.setForeground(new java.awt.Color(153, 0, 0));
        refuseButton.setText("Refuse");
        refuseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refuseButtonActionPerformed(evt);
            }
        });

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        logScrollPane.setViewportView(logTextArea);

        journalLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        journalLabel.setForeground(new java.awt.Color(0, 0, 0));
        journalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        journalLabel.setText("Journal");

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        infoButton.setText("Info");
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
                GuiEvent guiEvent = new GuiEvent(this, EXIT_FRAME_CODE);
                customerAgent.postGuiEvent(guiEvent);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(agentNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addComponent(logScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(journalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(selectLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(priceValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(productsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(infoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(acceptButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(refuseButton)
                                                .addGap(90, 90, 90))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 132, Short.MAX_VALUE)
                                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(133, 133, 133))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(agentName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(agentNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(productsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(infoButton))
                                .addGap(18, 18, 18)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(priceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(acceptButton)
                                        .addComponent(refuseButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(journalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }


    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int index = productsComboBox.getSelectedIndex();

        if (index > 0) {

            String key = productsComboBox.getItemAt(index);

            Product product = stringProductMap.getOrDefault(key, null);

            if (product != null) {
                GuiEvent guiEvent = new GuiEvent(this, BUY_FRAME_CODE);
                guiEvent.addParameter(product);
                customerAgent.postGuiEvent(guiEvent);
            }
        }
    }

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int index = productsComboBox.getSelectedIndex();

        if (index > 0) {

            String key = productsComboBox.getItemAt(index);

            Product product = stringProductMap.getOrDefault(key, null);

            if (product != null) {
                GuiEvent guiEvent = new GuiEvent(this, ACCEPT_FRAME_CODE);
                customerAgent.postGuiEvent(guiEvent);
            }
        }
    }

    private void refuseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        lockDecisionButtons();
    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {
        GuiEvent guiEvent = new GuiEvent(this, STOP_FRAME_CODE);
        customerAgent.postGuiEvent(guiEvent);
    }

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int index = productsComboBox.getSelectedIndex();

        if (index > 0) {
            String key = productsComboBox.getItemAt(index);

            Product product = stringProductMap.getOrDefault(key, null);

            if (product != null) {
                JOptionPane.showMessageDialog(this, product.getDescription(), "Product information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void lockDecisionButtons() {
        acceptButton.setEnabled(false);
        refuseButton.setEnabled(false);
        acceptButton.setVisible(false);
        refuseButton.setVisible(false);
        priceLabel.setVisible(false);
        priceValue.setVisible(false);
        productsComboBox.setEnabled(true);
        searchButton.setEnabled(true);
    }

    public void unlockDecisionButtons() {
        acceptButton.setEnabled(true);
        refuseButton.setEnabled(true);
        acceptButton.setVisible(true);
        refuseButton.setVisible(true);
        priceLabel.setVisible(true);
        priceValue.setVisible(true);
        productsComboBox.setEnabled(false);
        searchButton.setEnabled(false);
    }

    public void printLog(String line) {
        logTextArea.append(line + '\n');
    }

    public CustomerAgent getCustomerAgent() {
        return customerAgent;
    }

    public void setOrderProposal(OrderProposal orderProposal) {

        if (orderProposal.getProduct() != null) {

            Product product = orderProposal.getProduct();

            int index = -1;

            for (int i = 0; i < productsComboBox.getItemCount(); i++) {
                if (productsComboBox.getItemAt(i).equals(product.toString())) {
                    index = i;
                    break;
                }
            }

            if (index > 0) {
                productsComboBox.setSelectedIndex(index);
            } else {
                productsComboBox.addItem(product.toString());
                productsComboBox.setSelectedIndex(productsComboBox.getItemCount() - 1);
            }

            priceValue.setText("$" + orderProposal.getPrice());
        }
    }
}
