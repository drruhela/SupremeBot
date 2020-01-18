/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoPayBot;

import java.io.File;
import javax.swing.DefaultComboBoxModel;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 * @author drruh
 */
public class informationViewer extends javax.swing.JFrame {

    Document doc;
    Element root;
    Element selectedCard;
    
    /**
     * Creates new form informationViewer
     */
    public informationViewer() {
        initComponents();
        
        File file = new File("cards.xml");
        readXML(file);
        
        int elements = root.getChildElements().size();
        
        selectedCard = root.getChildElements().get(0);
        
        
        /*
        DefaultComboBoxModel cardSelector = new DefaultComboBoxModel();
        
        
        for (int element = 0; element < elements; element++) {
            String cardType = root.getChildElements().get(element).getFirstChildElement("type").getValue();
            
            cardSelector.addElement(cardType);
            
             
        }*/
        
        DefaultComboBoxModel cardSelector = new DefaultComboBoxModel();
        cardSelector.addElement("Example");
        cardSelector.addElement("Primary");
        cardSelector.addElement("Secondary");
        
        jComboBoxCardSelection.setModel(cardSelector);
        
        
        String name = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("name").getValue();
        String email = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("email").getValue();
        String phone = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("phone").getValue();
                
        String unit = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("unit").getValue();
        String streetAddress = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("streetAddress").getValue();
        String city = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("city").getValue();
        String postal = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("postal").getValue();
        String state = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("state").getValue();
        String country = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("country").getValue();
                
        String number = selectedCard.getFirstChildElement("number").getValue();
                
        String month = selectedCard.getFirstChildElement("expiry").getFirstChildElement("month").getValue();
        String year = selectedCard.getFirstChildElement("expiry").getFirstChildElement("year").getValue();
                
        String securityCode = selectedCard.getFirstChildElement("securityCode").getValue();
        
        if (unit.compareTo("") != 0) {
            streetAddress = unit + "-" + streetAddress;
        }
        
        jLabelCardHolderName.setText(name);
        jLabelEmail.setText(email);
        jLabelPhoneNumber.setText(phone);
        jLabelStreetAddress.setText(streetAddress);
        jLabelCity.setText(city);
        jLabelPostalCode.setText(postal);
        jLabelState.setText(state);
        jLabelCountry.setText(country);
        jLabelCardNumber.setText(number);
        jLabelMonth.setText(month);
        jLabelYear.setText(year);
        jLabelSecurityCode.setText(securityCode);
        
        
    }
            
    
    public void readXML(File file) {
        
        Builder builder = new Builder();
        
        if (file.exists()) {
            
            try {
                doc = builder.build(file);
                root = doc.getRootElement();
                Elements cards = root.getChildElements();
                
            } catch (Exception e) {          
                e.printStackTrace();
            
            }
        
        }
        
        else {
            
            root = new Element("cards");
            doc = new Document(root);
        }
    }
    
    public void changeInfo() {
        String selection = jComboBoxCardSelection.getSelectedItem().toString();
        
        int elements = root.getChildElements().size();
                     
        for (int element = 0; element < elements; element++) {
            
            String cardType = root.getChildElements().get(element).getFirstChildElement("type").getValue();
            
            if (selection.compareTo(cardType) == 0) {
                
                selectedCard = root.getChildElements().get(element);
  
            }
            
            
        }
        
        
        String name = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("name").getValue();
        String email = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("email").getValue();
        String phone = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("phone").getValue();
        
        String unit = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("unit").getValue();
        String streetAddress = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("streetAddress").getValue();
        String city = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("city").getValue();
        String postal = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("postal").getValue();
        String state = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("state").getValue();
        String country = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("country").getValue();
                
        String number = selectedCard.getFirstChildElement("number").getValue();
                
        String month = selectedCard.getFirstChildElement("expiry").getFirstChildElement("month").getValue();
        String year = selectedCard.getFirstChildElement("expiry").getFirstChildElement("year").getValue();
                
        String securityCode = selectedCard.getFirstChildElement("securityCode").getValue();
        
        if (unit.compareTo("") != 0) {
            streetAddress = unit + "-" + streetAddress;
        }
        
        jLabelCardHolderName.setText(name);
        jLabelEmail.setText(email);
        jLabelPhoneNumber.setText(phone);
        jLabelStreetAddress.setText(streetAddress);
        jLabelCity.setText(city);
        jLabelPostalCode.setText(postal);
        jLabelState.setText(state);
        jLabelCountry.setText(country);
        jLabelCardNumber.setText(number);
        jLabelMonth.setText(month);
        jLabelYear.setText(year);
        jLabelSecurityCode.setText(securityCode);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCardSelection = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        returnHomeButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabelCardHolderName = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jLabelStreetAddress = new javax.swing.JLabel();
        jLabelCity = new javax.swing.JLabel();
        jLabelState = new javax.swing.JLabel();
        jLabelPostalCode = new javax.swing.JLabel();
        jLabelCountry = new javax.swing.JLabel();
        jLabelCardNumber = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jLabelSecurityCode = new javax.swing.JLabel();
        jLabelMonth = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 247, 244));

        jLabel1.setFont(new java.awt.Font("Calisto MT", 1, 36)); // NOI18N
        jLabel1.setText("Cards Manager");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(236, 247, 244));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel2.setText("Card:");

        jComboBoxCardSelection.setBackground(new java.awt.Color(204, 255, 204));
        jComboBoxCardSelection.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxCardSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Example", "Primary", "Secondary" }));
        jComboBoxCardSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCardSelectionActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel3.setText("Card Holder:");

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel5.setText("Phone Number:");

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel6.setText("Street Address:");

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel7.setText("City:");

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel9.setText("State/Province:");

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel8.setText("Postal Code:");

        jLabel12.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel12.setText("Country:");

        jLabel10.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel10.setText("Card Number:");

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel13.setText("Expiry Date:");

        jLabel11.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel11.setText("Security Code:");

        jLabel14.setText("Developed by Devika");

        returnHomeButton.setBackground(new java.awt.Color(204, 255, 204));
        returnHomeButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        returnHomeButton.setText("Return To Control Center");
        returnHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnHomeButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel15.setText("Year:");

        jLabelCardHolderName.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelCardHolderName.setText("jLabel16");

        jLabelEmail.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelEmail.setText("jLabel16");

        jLabelPhoneNumber.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelPhoneNumber.setText("jLabel16");

        jLabelStreetAddress.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelStreetAddress.setText("jLabel16");

        jLabelCity.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelCity.setText("jLabel16");

        jLabelState.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelState.setText("jLabel16");

        jLabelPostalCode.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelPostalCode.setText("jLabel16");

        jLabelCountry.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelCountry.setText("jLabel16");

        jLabelCardNumber.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelCardNumber.setText("jLabel16");

        jLabelYear.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelYear.setText("jLabel16");

        jLabelSecurityCode.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelSecurityCode.setText("jLabel16");

        jLabelMonth.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabelMonth.setText("jLabel16");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxCardSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelState, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(jLabelCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabelStreetAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelSecurityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(returnHomeButton)))))
                .addGap(40, 40, 40))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelCardHolderName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCardSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCardHolderName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSecurityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStreetAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelState, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(returnHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void returnHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnHomeButtonActionPerformed
        // TODO add your handling code here:
        new paymentInfo().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returnHomeButtonActionPerformed

    private void jComboBoxCardSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCardSelectionActionPerformed
        // TODO add your handling code here:
        changeInfo();
    }//GEN-LAST:event_jComboBoxCardSelectionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(informationViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informationViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informationViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informationViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new informationViewer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxCardSelection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCardHolderName;
    private javax.swing.JLabel jLabelCardNumber;
    private javax.swing.JLabel jLabelCity;
    private javax.swing.JLabel jLabelCountry;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelPostalCode;
    private javax.swing.JLabel jLabelSecurityCode;
    private javax.swing.JLabel jLabelState;
    private javax.swing.JLabel jLabelStreetAddress;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton returnHomeButton;
    // End of variables declaration//GEN-END:variables
}
