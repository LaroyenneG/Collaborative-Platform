package collaborative.platform.gui;

import collaborative.platform.agents.CustomerAgent;

import javax.swing.*;

public class CustomerGUI extends JFrame {

    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel agentName;
    private javax.swing.JLabel agentNameLabel;
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

    public CustomerGUI(CustomerAgent customerAgent) {
        this.customerAgent = customerAgent;
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Collaborative Platform");

        agentNameLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        agentNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        agentNameLabel.setText("Agent :");

        agentName.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
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
        journalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        journalLabel.setText("Journal");

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(agentNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stopButton)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(133, 133, 133))
                        .addComponent(logScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(journalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(productsComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(acceptButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(refuseButton)
                                                .addGap(78, 78, 78))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(priceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(agentNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                        .addComponent(agentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(selectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(acceptButton)
                                        .addComponent(refuseButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(journalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(evt);
    }

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(evt);
    }

    private void refuseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(evt);
    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(evt);
    }

    public CustomerAgent getCustomerAgent() {
        return customerAgent;
    }
}
