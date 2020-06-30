import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sat Apr 11 00:08:08 IST 2020
 */



/**
 * @author Prakash
 */
public class PassengerLogin extends JFrame {
    public PassengerLogin() {
        initComponents();
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    private void label4MouseClicked(MouseEvent e) {
        this.setVisible(false);
        new PassengerSignUp().setVisible(true);
    }

    private void thisMouseClicked(MouseEvent e) {
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }

    private void label5MouseClicked(MouseEvent e) {
        this.setVisible(false);
        new HomePage().setVisible(true);
    }
    public String mob;
    private void button1MouseClicked(MouseEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            mob = textField1.getText();
            String pas = String.valueOf(passwordField1.getPassword());

            String initVector = "porysbmiro2h5nro";
            String key = "irbfuskifunucoaj";
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);


            byte[] encrypted = cipher.doFinal(pas.getBytes());
            String ptext =  Base64.getEncoder().encodeToString(encrypted);

            PreparedStatement pst = con.prepareStatement("select * from Passenger where contact = ?");
            pst.setString(1,mob);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getString("contact").equals(mob) && rs.getString("password").equals(ptext))
                {
                    JOptionPane.showMessageDialog(null,"Login Successful");
                    this.setVisible(false);
                    new PassengerMain(mob).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Check Credentials");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Check Credentials");
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void textField1FocusLost(FocusEvent e) {
        String x = textField1.getText();

        try {
            if(x.length() == 0){
                throw new Exception("Contact must be of 10 digits and contain only numbers");
            }
            for (int i = 0; i < x.length(); i++) {
                if (!(Character.isDigit(x.charAt(i))) || (x.length() < 10 || x.length() > 10)) {
                    textField1.setText("");
                    throw new Exception("Contact must be of 10 digits and contain only numbers");
                }
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Contact must be of 10 digits and contain only numbers");
        }
    }

    private void passwordField1FocusLost(FocusEvent e) {
    }

    private void label3MouseClicked(MouseEvent e) {
        new fpassp().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        label3 = new JLabel();
        button1 = new JButton();
        label4 = new JLabel();
        label5 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        setTitle("Passenger Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Mobile Number :");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField1FocusLost(e);
            }
        });

        //---- label2 ----
        label2.setText("Password :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordField1FocusLost(e);
            }
        });

        //---- label3 ----
        label3.setText("Forgot Password?");
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label3MouseClicked(e);
            }
        });

        //---- button1 ----
        button1.setText("Login");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- label4 ----
        label4.setText("New User? Click Here to Sign Up");
        label4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label4MouseClicked(e);
            }
        });

        //---- label5 ----
        label5.setIcon(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifyfin1.jpg"));
        label5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label5MouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(77, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label5)
                    .addGap(0, 343, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label5)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(54, 54, 54))
        );
        setSize(425, 240);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JLabel label3;
    private JButton button1;
    private JLabel label4;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
