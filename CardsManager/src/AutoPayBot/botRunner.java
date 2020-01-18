/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoPayBot;

import java.awt.Color;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlEmailInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drruh
 */
public class botRunner extends javax.swing.JFrame {

    Document doc;
    Element root;
    Element selectedCard;
    
    /**
     * Creates new form botRunner
     */
    public botRunner() {
        initComponents();
        
        File file = new File("cards.xml");
        readXML(file);
        
        selectedCard = root.getChildElements().get(0);
        
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
    
    public void retrieveCardInfo() {
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
        String city = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("city").getValue();
        String postal = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("postal").getValue();
        String state = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("state").getValue();
        String country = selectedCard.getFirstChildElement("cardHolder").getFirstChildElement("address").getFirstChildElement("country").getValue();
                
        String number = selectedCard.getFirstChildElement("number").getValue();
                
        String month = selectedCard.getFirstChildElement("expiry").getFirstChildElement("month").getValue();
        String year = selectedCard.getFirstChildElement("expiry").getFirstChildElement("year").getValue();
                
        String securityCode = selectedCard.getFirstChildElement("securityCode").getValue();
    }
    
    public void getProduct() {
        
        String url = "https://www.supremenewyork.com/shop/all";
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        
        try {
            HtmlPage mainPage = client.getPage(url);
            
            String category = jComboBoxCategorySelection.getSelectedItem().toString();
            String categoryLinkString = "/shop/all/" + category;
            HtmlAnchor categoryLink = mainPage.getAnchorByHref(categoryLinkString);
            
            HtmlPage shopPage = categoryLink.click();
            
            List<HtmlDivision> products = shopPage.getByXPath("//div[contains(@class, 'inner-article')]");
            int amount = products.size();
            
            List<HtmlAnchor> anchors = shopPage.getByXPath("//a[contains(@class, 'name-link')]");
            
            List<HtmlDivision> notSold = new ArrayList<>();
            List<HtmlDivision> collection = new ArrayList<>();
            List<HtmlDivision> preferred = new ArrayList<>();
            
            String keywords = jTextFieldKeywords.getText().toLowerCase();
            String color = jTextFieldColor.getText().toLowerCase();
            
            
            HtmlDivision chosen = null;
            //getting list of applicable products
            for (int i = 0; i < amount; i++) {
                
                HtmlDivision product = products.get(i);
                
                if (!(product.asText().contains("sold out"))) {
                    
                    //adds available products in the selected category to this list
                    notSold.add(product);
                    
                    if (product.asText().toLowerCase().contains(keywords)) {
                        
                        collection.add(product);
                        
                        if (product.asText().toLowerCase().contains(color)) {
                            
                            preferred.add(product);
                            
                        }
                        
                    }
                }
            }
            
            String message = "Looking for Product.";
            
            if (!(notSold.isEmpty())) {
                
                if (!(collection.isEmpty())) {
                    
                    if (!preferred.isEmpty()) {
                        chosen = preferred.get(0);
                    } else {
                        chosen = collection.get(0);
                    }    
                } else {
                    message = "Nothing Available.";
                    
                }
                
            } else {
                System.out.println("All sold out.");
                message = "Sorry, all " + category + " are sold out.";
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
            
            if (chosen != null) {
                int index = products.indexOf(chosen);
                System.out.println(index);
                double num = ((index * 1.0) / (amount * 1.0) * (anchors.size() * 1.0));
                int newIndex = (int) Math.round(num);
                
                HtmlAnchor productAnchor = anchors.get(newIndex);
                HtmlAnchor productColor = anchors.get(newIndex+1);
                HtmlPage productPage = productAnchor.click();
                
                message = productAnchor.asText() + " (" + productColor.asText() + ")";
                
                //System.out.println(productPage.asXml());
                
                HtmlSubmitInput addToCart = productPage.getElementByName("commit");
                
                HtmlPage submitted = addToCart.click();
                
                String checkoutURL = "https://www.supremenewyork.com/checkout";
                
                HtmlAnchor checkOut = submitted.getAnchorByHref(checkoutURL);
                
                HtmlPage cart = checkOut.click();
                System.out.println(cart.asText());
                
                HtmlInput nameField = cart.getElementByName("order[billing_name]");
                HtmlInput emailField = cart.getElementByName("order[email]");
                HtmlInput phoneField = cart.getElementByName("order[tel]");
                HtmlInput streetAddressField = cart.getElementByName("order[billing_address]");
                HtmlInput unitField = cart.getElementByName("order[billing_address_2]");
                HtmlInput zipField = cart.getElementByName("order[billing_zip]");
                HtmlInput cityField = cart.getElementByName("order[billing_city]");
                HtmlInput creditCardField = cart.getElementByName("credit_card[nlb]");
                HtmlInput securityField = cart.getElementByName("credit_card[rvv]");
                
                
                HtmlSelect countryDropdown = (HtmlSelect) cart.getElementById("order_billing_country");
                HtmlOption countryOption = countryDropdown.getOptionByValue(country.toUpperCase());
                countryDropdown.setSelectedAttribute(countryOption, true);
                
                
                
                nameField.type(name);
                emailField.type(email);
                phoneField.type(phone);
                streetAddressField.type(streetAddress);
                
                if (unit.compareTo("") != 0) {
                    unitField.type(unit);
                }
                
                zipField.type(postal);
                cityField.type(city);
                creditCardField.type(number);
                
                HtmlSelect monthDropdown = (HtmlSelect) cart.getElementById("credit_card_month");
                HtmlOption monthOption = monthDropdown.getOptionByValue(month);
                monthDropdown.setSelectedAttribute(monthOption, true);
                
                HtmlSelect yearDropdown = (HtmlSelect) cart.getElementById("credit_card_year");
                HtmlOption yearOption = yearDropdown.getOptionByValue(year);
                yearDropdown.setSelectedAttribute(yearOption, true);
                
                securityField.type(securityCode);
                
                countryOption = countryDropdown.getOptionByValue(country.toUpperCase());
                countryDropdown.setSelectedAttribute(countryOption, true);
                
                HtmlCheckBoxInput addressCheckbox = cart.getHtmlElementById("store_address");
                addressCheckbox.setChecked(true);
                
                HtmlSelect stateDropdown = (HtmlSelect) cart.getElementById("order_billing_state");
                //HtmlOption stateOption = stateDropdown.getOptionByValue(state.toUpperCase());
                //stateDropdown.setSelectedAttribute(stateOption, true);
                stateDropdown.type(state.toUpperCase());
                
                HtmlCheckBoxInput termsCheckbox = cart.getHtmlElementById("order_terms");
                termsCheckbox.setChecked(true);
                
                System.out.println(cart.asText());
                
                HtmlSubmitInput completePayment = cart.getElementByName("commit");
                HtmlPage lastPage = completePayment.click();
                System.out.println(lastPage.asText());
                
            }
            
            productDisplay display = new productDisplay(message);
            display.setVisible(true);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ButtonStartBot = new javax.swing.JButton();
        ButtonMainMenu = new javax.swing.JButton();
        jComboBoxCardSelection = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCategorySelection = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldKeywords = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxSizeSelection = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldPriceLimit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldColor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 247, 244));

        jLabelTitle.setFont(new java.awt.Font("Calisto MT", 1, 30)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("AutoPay Bot");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(236, 247, 244));

        jLabel3.setText("Developed by DevDev");

        ButtonStartBot.setBackground(new java.awt.Color(204, 255, 204));
        ButtonStartBot.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        ButtonStartBot.setText("Start Bot");
        ButtonStartBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStartBotActionPerformed(evt);
            }
        });

        ButtonMainMenu.setBackground(new java.awt.Color(204, 255, 204));
        ButtonMainMenu.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        ButtonMainMenu.setText("Main Menu");
        ButtonMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMainMenuActionPerformed(evt);
            }
        });

        jComboBoxCardSelection.setBackground(new java.awt.Color(204, 255, 204));
        jComboBoxCardSelection.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxCardSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primary", "Secondary", "Example" }));
        jComboBoxCardSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCardSelectionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel4.setText("What Are You Looking For?");

        jComboBoxCategorySelection.setBackground(new java.awt.Color(204, 255, 204));
        jComboBoxCategorySelection.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxCategorySelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "jackets", "shirts", "tops/sweaters", "sweatshirts", "pants", "hats", "accessories", "shoes", "skate" }));
        jComboBoxCategorySelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorySelectionActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Card:");

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Category:");

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Keywords:");

        jTextFieldKeywords.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldKeywords.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldKeywords.setText("eg. box logo");
        jTextFieldKeywords.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldKeywordsFocusGained(evt);
            }
        });
        jTextFieldKeywords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKeywordsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Size:");

        jComboBoxSizeSelection.setBackground(new java.awt.Color(204, 255, 204));
        jComboBoxSizeSelection.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxSizeSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL", "Any Size" }));
        jComboBoxSizeSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSizeSelectionActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Price Limit (USD):");

        jTextFieldPriceLimit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("(Leave empty for no limit)");

        jLabel12.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("(If size unavailable, random size will be bought.)");

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Color:");

        jTextFieldColor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldColor.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldColor.setText("eg. green");
        jTextFieldColor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldColorFocusGained(evt);
            }
        });
        jTextFieldColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldColorActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("(Random color will be bought if the preferred is unavailable.)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ButtonStartBot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ButtonMainMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jComboBoxCardSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldKeywords))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jComboBoxCategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldColor)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(138, 138, 138)
                                        .addComponent(jLabel14))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxSizeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPriceLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCardSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSizeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPriceLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(ButtonStartBot, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addContainerGap())
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

    private void ButtonStartBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStartBotActionPerformed
        // TODO add your handling code here:
        
        getProduct();
        
    }//GEN-LAST:event_ButtonStartBotActionPerformed

    private void ButtonMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonMainMenuActionPerformed
        // TODO add your handling code here:
        new botMainWindow().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ButtonMainMenuActionPerformed

    private void jComboBoxCardSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCardSelectionActionPerformed
        // TODO add your handling code here:
        retrieveCardInfo();
    }//GEN-LAST:event_jComboBoxCardSelectionActionPerformed

    private void jComboBoxCategorySelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategorySelectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCategorySelectionActionPerformed

    private void jTextFieldKeywordsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldKeywordsFocusGained
        // TODO add your handling code here:
        jTextFieldKeywords.setText("");
        jTextFieldKeywords.setForeground(Color.BLACK);
    }//GEN-LAST:event_jTextFieldKeywordsFocusGained

    private void jTextFieldKeywordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKeywordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKeywordsActionPerformed

    private void jComboBoxSizeSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSizeSelectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSizeSelectionActionPerformed

    private void jTextFieldColorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldColorFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldColorFocusGained

    private void jTextFieldColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldColorActionPerformed

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
            java.util.logging.Logger.getLogger(botRunner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(botRunner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(botRunner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(botRunner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new botRunner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonMainMenu;
    private javax.swing.JButton ButtonStartBot;
    private javax.swing.JComboBox<String> jComboBoxCardSelection;
    private javax.swing.JComboBox<String> jComboBoxCategorySelection;
    private javax.swing.JComboBox<String> jComboBoxSizeSelection;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldColor;
    private javax.swing.JTextField jTextFieldKeywords;
    private javax.swing.JTextField jTextFieldPriceLimit;
    // End of variables declaration//GEN-END:variables
}
