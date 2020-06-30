import jdk.nashorn.internal.runtime.ECMAException;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Base64;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.InternalFrameEvent;
/*
 * Created by JFormDesigner on Fri Apr 10 16:42:56 IST 2020
 */



/**
 * @author Prakash
 */
public class SignUpDriver extends JFrame {
    public SignUpDriver() {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        bg.add(radioButton3);
    }

    private void thisMouseClicked(MouseEvent e) {
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }

    private void label4MouseClicked(MouseEvent e) {
        this.setVisible(false);
        new HomePage().setVisible(true);
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            String name = textField5.getText();
            String phone = textField7.getText();
            String lic = textField1.getText();
            String pass = String.valueOf(passwordField1.getPassword());

            String initVector = "purisbkflaco89fh";
            String key = "94hjiqpolzmnc34z";
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);

            byte[] encrypted = cipher.doFinal(pass.getBytes());
            String ptext =  Base64.getEncoder().encodeToString(encrypted);
            String email = textField2.getText();
            String ag = spinner2.getValue() + "";
            int age= Integer.parseInt(ag);

            String st = spinner1.getValue() + "";
            int seat = Integer.parseInt(st);
            String type = comboBox1.getSelectedItem() + "";
            String carno = textField10.getText();
            String cname = textField11.getText();
            String g = null;
            if(radioButton1.isSelected())
            {
                g = "M";
            }
            else if(radioButton2.isSelected())
            {
                g = "F";
            }
            else if(radioButton3.isSelected()) {
                g = "T";
            }
            String insert_query = "insert into Driver values (? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(insert_query);
            pst.setString(1,name);
            pst.setString(2,phone);
            pst.setString(3,ptext);
            pst.setString(4,lic);
            pst.setInt(5,age);
            pst.setString(6,g);
            pst.setString(7,type);
            pst.setString(8,carno);
            pst.setString(9,cname);
            pst.setInt(10,seat);
            pst.setInt(11, 0);
            pst.setString(12,email);
            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null,"Thank you for registering with us");
            this.setVisible(false);
            new LoginDriver().setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void textField5FocusLost(FocusEvent e) {
        try {
            String n = textField5.getText();
            for (int i = 0; i < n.length(); i++) {
                char ch = n.charAt(i);
                if ((!(ch >= 'A' && ch <= 'Z'))
                        && (!(ch >= 'a' && ch <= 'z'))) {
                    textField5.setText("");
                    throw new Exception("Enter a Valid Name");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Name");
        }

    }

    private void textField7FocusLost(FocusEvent e) {

        String x = textField7.getText();

        try {
            for (int i = 0; i < x.length(); i++) {
                if (!(Character.isDigit(x.charAt(i))) || (x.length() < 10 || x.length() > 10)) {
                    textField7.setText("");
                    throw new Exception("Contact must be of 10 digits and contain only numbers");
                }
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Contact must be of 10 digits and contain only numbers");
        }
    }

    private void passwordField1FocusLost(FocusEvent e) {
        String p = String.valueOf(passwordField1.getPassword());
        try {
            if(p.length() < 7){
                passwordField1.setText("");
                throw new Exception("Password must atleast be 8 characters");
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Password must atleast be 8 characters");
        }
    }

    private void textField1FocusLost(FocusEvent e) {
        String l = textField1.getText();
        try{
            if((l.length() >15 || l.length() < 15)){
                textField1.setText("");
                throw new Exception("Invalid License Number");
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Invalid License Number");
        }
    }

    private void textField10FocusLost(FocusEvent e) {
        String cn = textField10.getText();
        try {
            if ((cn.length() > 10 || cn.length() < 7)) {
                textField10.setText("");
                throw new Exception("Invalid Car Registration Number");
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Invalid Car Registeration Number");
        }
    }

    private void textField11FocusLost(FocusEvent e) {
        try {
            String n = textField11.getText();
            for (int i = 0; i < n.length(); i++) {
                char ch = n.charAt(i);
                if ((!(ch >= 'A' && ch <= 'Z'))
                        && (!(ch >= 'a' && ch <= 'z'))) {
                    textField11.setText("");
                    throw new Exception("Enter a Valid Car Name");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Car Name");
        }
    }

    private void button1MouseEntered(MouseEvent e) {
        try{
        if((!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected()) || (textField11.getText().length()==0) || (textField7.getText().length()==0) || (textField5.getText().length()==0)){
          throw new Exception("Please enter all the Details");
        }}
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Please enter all the Details");
        }
    }

    private void textField2FocusLost(FocusEvent e) {
        String x = textField2.getText();
        try{
            if(x.length() == 0){
                throw new Exception("Please enter a valid Email-ID");
            }
            String email = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(email);
            boolean b= pat.matcher(x).matches();
            if(!b){
                throw new Exception("Please enter a valid Email-ID");
            }

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid Email-ID");
        }
    }

    private void spinner2FocusLost(FocusEvent e) {
        String ag = spinner2.getValue() + "";
        int age= Integer.parseInt(ag);
        try{
        if(age<18){
            throw new Exception("You are not eligible to apply as a Driver");
        }}
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"You are not eligible to apply as a Driver");
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        button1 = new JButton();
        textField5 = new JTextField();
        label6 = new JLabel();
        textField7 = new JTextField();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        textField10 = new JTextField();
        textField11 = new JTextField();
        radioButton3 = new JRadioButton();
        passwordField1 = new JPasswordField();
        label4 = new JLabel();
        label11 = new JLabel();
        textField1 = new JTextField();
        comboBox1 = new JComboBox<>();
        label12 = new JLabel();
        label13 = new JLabel();
        spinner2 = new JSpinner();
        label14 = new JLabel();
        textField2 = new JTextField();
        spinner1 = new JSpinner();

        //======== this ========
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        setTitle("Driver-Sign Up");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Personal Details");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        //---- label2 ----
        label2.setText("Name :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label3 ----
        label3.setText("Contact :");
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label5 ----
        label5.setText("Password :");
        label5.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- button1 ----
        button1.setText("Sign Up!");
        button1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button1MouseEntered(e);
            }
        });

        //---- textField5 ----
        textField5.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField5FocusLost(e);
            }
        });

        //---- label6 ----
        label6.setText("Gender :");
        label6.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField7 ----
        textField7.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField7FocusLost(e);
            }
        });

        //---- radioButton1 ----
        radioButton1.setText("Male");
        radioButton1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- radioButton2 ----
        radioButton2.setText("Female");
        radioButton2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label7 ----
        label7.setText("Car Details");
        label7.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        //---- label8 ----
        label8.setText("Car Type :");
        label8.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label9 ----
        label9.setText("Car Number :");
        label9.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label10 ----
        label10.setText("Car Name :");
        label10.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField10 ----
        textField10.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField10FocusLost(e);
            }
        });

        //---- textField11 ----
        textField11.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField11FocusLost(e);
            }
        });

        //---- radioButton3 ----
        radioButton3.setText("Transgender");
        radioButton3.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordField1FocusLost(e);
            }
        });

        //---- label4 ----
        label4.setIcon(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifyfin1.jpg"));
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label4MouseClicked(e);
            }
        });

        //---- label11 ----
        label11.setText("License No :");
        label11.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField1FocusLost(e);
            }
        });

        //---- comboBox1 ----
        comboBox1.setPrototypeDisplayValue("Select Type");
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Micro",
            "Mini",
            "Sedan",
            "SUV"
        }));

        //---- label12 ----
        label12.setText("Seat Capacity :");
        label12.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label13 ----
        label13.setText("Age :");
        label13.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- spinner2 ----
        spinner2.setModel(new SpinnerNumberModel(18, 18, 99, 1));
        spinner2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                spinner2FocusLost(e);
            }
        });

        //---- label14 ----
        label14.setText("Email-ID :");
        label14.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField2 ----
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField2FocusLost(e);
            }
        });

        //---- spinner1 ----
        spinner1.setModel(new SpinnerNumberModel(2, 2, 6, 2));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label5, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textField7)
                                        .addComponent(textField5)
                                        .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label13, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label11, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                        .addComponent(label14, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textField1)
                                        .addComponent(spinner2, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                        .addComponent(textField2, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))))
                            .addGap(171, 171, 171)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label12, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label10, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textField11, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addComponent(spinner1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboBox1))
                                    .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textField10, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(label4)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(radioButton2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(radioButton3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(74, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label4)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label14, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinner2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButton2)
                        .addComponent(radioButton3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                    .addGap(39, 39, 39)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25))
        );
        setSize(790, 530);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JButton button1;
    private JTextField textField5;
    private JLabel label6;
    private JTextField textField7;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JTextField textField10;
    private JTextField textField11;
    private JRadioButton radioButton3;
    private JPasswordField passwordField1;
    private JLabel label4;
    private JLabel label11;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JLabel label12;
    private JLabel label13;
    private JSpinner spinner2;
    private JLabel label14;
    private JTextField textField2;
    private JSpinner spinner1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
