/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoPayBot;

import java.awt.Color;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Serializer;
/**
 *
 * @author drruh
 */
public class informationEditor extends javax.swing.JFrame {

    Document doc;
    Element root;
    Element selectedCard;
    
    /**
     * Creates new form informationEditor
     */
    public informationEditor() {
        initComponents();
        
        File file = new File("cards.xml");
        readXML(file);
        
        int elements = root.getChildElements().size();
        
        if (elements >= 1) {
            selectedCard = root.getChildElements().get(0);
            String name = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("name").getValue();
            String email = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("email").getValue();
            String phone = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("phone").getValue();

            String streetAddress = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("streetAddress").getValue();
            String city = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("city").getValue();
            String postal = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("postal").getValue();
            String state = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("state").getValue();
            String country = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("country").getValue();

            String number = selectedCard.getFirstChildElement("number").getValue();

            String month = selectedCard.getFirstChildElement("expiry").getFirstChildElement("month").getValue();
            String year = selectedCard.getFirstChildElement("expiry").getFirstChildElement("year").getValue();

            String securityCode = selectedCard.getFirstChildElement("securityCode").getValue();

            jTextFieldCardHolderName.setText(name);
            jTextFieldEmail.setText(email);
            jTextFieldPhoneNumber.setText(phone);
            jTextFieldStreetAddress.setText(streetAddress);
            jTextFieldCity.setText(city);
            jTextFieldPostalCode.setText(postal);
            jTextFieldState.setText(state);
            jTextFieldCountry.setText(country);
            jTextFieldCardNumber.setText(number);
            jComboBoxMonths.setSelectedItem(month);
            jTextFieldYear.setText(year);
            jTextFieldSecurityCode.setText(securityCode);
        }
        
        else {
            jTextFieldCardHolderName.setText("");
            jTextFieldEmail.setText("");
            jTextFieldPhoneNumber.setText("");
            jTextFieldStreetAddress.setText("");
            jTextFieldCity.setText("");
            jTextFieldPostalCode.setText("");
            jTextFieldState.setText("");
            jTextFieldCountry.setText("");
            jTextFieldCardNumber.setText("");
            jComboBoxMonths.setSelectedItem("");
            jTextFieldYear.setText("");
            jTextFieldSecurityCode.setText("");
        }
        
        DefaultComboBoxModel cardSelector = new DefaultComboBoxModel();
        cardSelector.addElement("Example");
        cardSelector.addElement("Primary");
        cardSelector.addElement("Secondary");
        
        
        
        
        jComboBoxCardSelection.setModel(cardSelector);                
        
        
        
        
        
        
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
    
    public void writeXML() {
        
        Element card = new Element("card");
        
        Element type = new Element("type");
        type.appendChild(jComboBoxCardSelection.getSelectedItem().toString());
        
        Element cardHolder = new Element("cardHolder");
        
        Element name = new Element("name");
        name.appendChild(jTextFieldCardHolderName.getText());
        
        Element email = new Element("email");
        email.appendChild(jTextFieldEmail.getText());
        
        Element phone = new Element("phone");
        phone.appendChild(jTextFieldPhoneNumber.getText());
        
        Element address = new Element("address");
        
        Element streetAddress = new Element("streetAddress");
        streetAddress.appendChild(jTextFieldStreetAddress.getText());
        
        Element unit = new Element("unit");
        if (jTextFieldUnit.getText().compareTo(" unit #") == 0) {
            unit.appendChild("");
        } else {
            unit.appendChild(jTextFieldUnit.getText());
        }
        Element city = new Element("city");
        city.appendChild(jTextFieldCity.getText());
        
        Element postal = new Element("postal");
        postal.appendChild(jTextFieldPostalCode.getText());
        
        Element state = new Element("state");
        state.appendChild(jTextFieldState.getText());
        
        Element country = new Element("country");
        country.appendChild(jTextFieldCountry.getText());
        
        Element number = new Element("number");
        number.appendChild(jTextFieldCardNumber.getText());
        
        Element expiry = new Element("expiry");
        
        Element month = new Element("month");
        month.appendChild(jComboBoxMonths.getSelectedItem().toString());
        
        Element year = new Element("year");
        year.appendChild(jTextFieldYear.getText());
        
        Element securityCode = new Element("securityCode");
        securityCode.appendChild(jTextFieldSecurityCode.getText());
        
        
        address.appendChild(streetAddress);
        address.appendChild(unit);
        address.appendChild(city);
        address.appendChild(postal);
        address.appendChild(state);
        address.appendChild(country);
        
        cardHolder.appendChild(name);
        cardHolder.appendChild(email);
        cardHolder.appendChild(phone);
        cardHolder.appendChild(address);
        
        expiry.appendChild(month);
        expiry.appendChild(year);
        
        card.appendChild(type);
        card.appendChild(cardHolder);
        card.appendChild(number);
        card.appendChild(expiry);
        card.appendChild(securityCode);
        
        root.appendChild(card);
        System.out.println(jTextFieldUnit.getText());
        
        try {
            
            Serializer serializer = new Serializer(System.out);
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(doc);
            
        } catch (IOException ex) {
            
            System.err.println(ex);
            
        }
        
        try {
            
            FileWriter file = new FileWriter("cards.xml");
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(doc.toXML());
            writer.close();
            
        } catch (IOException e) {
            
            e.printStackTrace();
            
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
                
        String streetAddress = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("streetAddress").getValue();
        String unit = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("unit").getValue();
        String city = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("city").getValue();
        String postal = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("postal").getValue();
        String state = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("state").getValue();
        String country = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("country").getValue();
                
        String number = selectedCard.getFirstChildElement("number").getValue();
                
        String month = selectedCard.getFirstChildElement("expiry").getFirstChildElement("month").getValue();
        String year = selectedCard.getFirstChildElement("expiry").getFirstChildElement("year").getValue();
                
        String securityCode = selectedCard.getFirstChildElement("securityCode").getValue();
            
        
        
        jTextFieldCardHolderName.setText(name);
        jTextFieldEmail.setText(email);
        jTextFieldPhoneNumber.setText(phone);
        jTextFieldStreetAddress.setText(streetAddress);
        jTextFieldUnit.setText(unit);
        jTextFieldCity.setText(city);
        jTextFieldPostalCode.setText(postal);
        jTextFieldState.setText(state);
        jTextFieldCountry.setText(country);
        jTextFieldCardNumber.setText(number);
        jComboBoxMonths.setSelectedItem(month);
        jTextFieldYear.setText(year);
        jTextFieldSecurityCode.setText(securityCode);
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
        jTextFieldCardHolderName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldStreetAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldCity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldState = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldPostalCode = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCountry = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCardNumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxMonths = new javax.swing.JComboBox<>();
        jTextFieldYear = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSecurityCode = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        returnHomeButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldUnit = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(236, 247, 244));

        jPanel1.setBackground(new java.awt.Color(236, 247, 244));

        jLabel1.setFont(new java.awt.Font("Calisto MT", 1, 36)); // NOI18N
        jLabel1.setText("Cards Manager");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(236, 247, 244));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel2.setText("Card:");

        jComboBoxCardSelection.setBackground(new java.awt.Color(204, 255, 204));
        jComboBoxCardSelection.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxCardSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primary", "Secondary", "Example" }));
        jComboBoxCardSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCardSelectionActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel3.setText("Card Holder:");

        jTextFieldCardHolderName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        jTextFieldEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel5.setText("Phone Number:");

        jTextFieldPhoneNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel6.setText("Street Address:");

        jTextFieldStreetAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel7.setText("City:");

        jTextFieldCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel9.setText("State/Province:");

        jTextFieldState.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel8.setText("Postal Code:");

        jTextFieldPostalCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldPostalCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPostalCodeActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel12.setText("Country:");

        jTextFieldCountry.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel10.setText("Card Number:");

        jTextFieldCardNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel13.setText("Expiry Date:");

        jComboBoxMonths.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxMonths.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jTextFieldYear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel11.setText("Security Code:");

        jTextFieldSecurityCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldSecurityCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSecurityCodeActionPerformed(evt);
            }
        });

        jLabel14.setText("Developed by Devika");

        saveButton.setBackground(new java.awt.Color(204, 255, 204));
        saveButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

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

        jTextFieldUnit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldUnit.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldUnit.setText(" unit #");
        jTextFieldUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldUnitFocusGained(evt);
            }
        });
        jTextFieldUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUnitActionPerformed(evt);
            }
        });

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
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(returnHomeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldCity)
                                            .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextFieldCardHolderName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(44, 44, 44)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel13)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextFieldStreetAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextFieldUnit)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldSecurityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jComboBoxMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 11, Short.MAX_VALUE)))))
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
                    .addComponent(jTextFieldCardHolderName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMonths, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSecurityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldStreetAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(returnHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jTextFieldSecurityCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSecurityCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSecurityCodeActionPerformed

    private void returnHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnHomeButtonActionPerformed
        // TODO add your handling code here:
        new paymentInfo().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returnHomeButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        writeXML();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jTextFieldPostalCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPostalCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPostalCodeActionPerformed

    private void jComboBoxCardSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCardSelectionActionPerformed
        // TODO add your handling code here:
        changeInfo();
    }//GEN-LAST:event_jComboBoxCardSelectionActionPerformed

    private void jTextFieldUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUnitActionPerformed

    private void jTextFieldUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUnitFocusGained
        // TODO add your handling code here:
        jTextFieldUnit.setText("");
        jTextFieldUnit.setForeground(Color.BLACK);
    }//GEN-LAST:event_jTextFieldUnitFocusGained

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
            java.util.logging.Logger.getLogger(informationEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informationEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informationEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informationEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new informationEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxCardSelection;
    private javax.swing.JComboBox<String> jComboBoxMonths;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldCardHolderName;
    private javax.swing.JTextField jTextFieldCardNumber;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldCountry;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldPostalCode;
    private javax.swing.JTextField jTextFieldSecurityCode;
    private javax.swing.JTextField jTextFieldState;
    private javax.swing.JTextField jTextFieldStreetAddress;
    private javax.swing.JTextField jTextFieldUnit;
    private javax.swing.JTextField jTextFieldYear;
    private javax.swing.JButton returnHomeButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
