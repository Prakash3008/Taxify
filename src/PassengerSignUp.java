import java.awt.*;
import java.awt.event.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.lang.*;
import java.sql.*;
import java.security.*;
import java.util.*;
import java.util.regex.Pattern;
/*
 * Created by JFormDesigner on Fri Apr 10 23:53:30 IST 2020
 */



/**
 * @author Prakash
 */
public class PassengerSignUp extends JFrame {
    public PassengerSignUp() {
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

    private void label7MouseClicked(MouseEvent e) {
        this.setVisible(false);
        new HomePage().setVisible(true);
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            String name = textField1.getText();
            String phone = textField2.getText();
            String mail = textField3.getText();
            String pass = String.valueOf(passwordField1.getPassword());

            String initVector = "porysbmiro2h5nro";
            String key = "irbfuskifunucoaj";
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);

            byte[] encrypted = cipher.doFinal(pass.getBytes());
            String ptext =  Base64.getEncoder().encodeToString(encrypted);

            String ag = spinner1.getValue() + "";
            int age = Integer.parseInt(ag);
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
            String insert_query = "insert into Passenger values (? ,? ,? ,?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(insert_query);
            pst.setString(1,name);
            pst.setString(2, phone);
            pst.setString(3,mail);
            pst.setString(4,ptext);
            pst.setInt(5,age);
            pst.setString(6,g);
            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null,"Thank you for registering with us");
            this.setVisible(false);
            new PassengerLogin().setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void textField1FocusLost(FocusEvent e) {
        try {
            String n = textField1.getText();
            for (int i = 0; i < n.length(); i++) {
                char ch = n.charAt(i);
                if ((!(ch >= 'A' && ch <= 'Z'))
                        && (!(ch >= 'a' && ch <= 'z'))) {
                    textField1.setText("");
                    throw new Exception("Enter a Valid Name");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Name");
        }
    }

    private void textField2FocusLost(FocusEvent e) {
        String x = textField2.getText();

        try {
            for (int i = 0; i < x.length(); i++) {
                if (!(Character.isDigit(x.charAt(i))) || (x.length() < 10 || x.length() > 10)) {
                    textField2.setText("");
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
                throw new Exception("Invalid Password");
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Invalid Password");
        }
    }

    private void button1MouseEntered(MouseEvent e) {
        try{
            if((!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected()) || (textField1.getText().length()==0) || (textField2.getText().length()==0) || (textField3.getText().length()==0) ){
                throw new Exception("Please enter all the Details");
            }}
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Please enter all the Details");
        }
    }

    private void textField3FocusLost(FocusEvent e) {
        String x = textField3.getText();
        try{
            if(x.length() == 0){
                throw new Exception("Please enter a valid Email-ID");
            }
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
           boolean b= pat.matcher(x).matches();
           if(!b){
               throw new Exception("Please enter a valid Email-ID");
           }

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid Email-ID");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        passwordField1 = new JPasswordField();
        label5 = new JLabel();
        label6 = new JLabel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        button1 = new JButton();
        label7 = new JLabel();
        spinner1 = new JSpinner();

        //======== this ========
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        setTitle("Passenger Sign-Up");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
                thisMouseClicked(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Name :");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField1FocusLost(e);
            }
        });

        //---- label2 ----
        label2.setText("Contact :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField2 ----
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField2FocusLost(e);
            }
        });

        //---- label3 ----
        label3.setText("Email-ID :");
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField3 ----
        textField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField3FocusLost(e);
            }
        });

        //---- label4 ----
        label4.setText("Password :");
        label4.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordField1FocusLost(e);
            }
        });

        //---- label5 ----
        label5.setText("Age :");
        label5.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label6 ----
        label6.setText("Gender :");
        label6.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- radioButton1 ----
        radioButton1.setText("M");
        radioButton1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        //---- radioButton2 ----
        radioButton2.setText("F");
        radioButton2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        //---- radioButton3 ----
        radioButton3.setText("T");
        radioButton3.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        //---- button1 ----
        button1.setText("Sign Up!");
        button1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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

        //---- label7 ----
        label7.setIcon(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifyfin1.jpg"));
        label7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label7MouseClicked(e);
            }
        });

        //---- spinner1 ----
        spinner1.setModel(new SpinnerNumberModel(1, 1, 99, 1));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label7)
                    .addGap(412, 412, 412))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addComponent(textField2, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addComponent(textField3, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(radioButton2, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                                .addComponent(spinner1, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(radioButton3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(115, 115, 115)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label7)
                    .addGap(9, 9, 9)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButton2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioButton3))
                    .addGap(18, 18, 18)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE))
        );
        setSize(385, 450);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JPasswordField passwordField1;
    private JLabel label5;
    private JLabel label6;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JButton button1;
    private JLabel label7;
    private JSpinner spinner1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
