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
 * Created by JFormDesigner on Tue Apr 14 15:44:50 IST 2020
 */



/**
 * @author Prakash
 */
public class fpassp extends JFrame {
    public fpassp() {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        label2.setVisible(false);
        label3.setVisible(false);
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            Statement smt = con.createStatement();
            String mob = textField1.getText();

            PreparedStatement pst = con.prepareStatement("select * from Passenger where contact = ?");
            pst.setString(1,mob);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getString("contact").equals(mob))
                {
                    String pass=rs.getString("password");
                    String initVector = "porysbmiro2h5nro";
                    String key = "irbfuskifunucoaj";
                    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

                    byte[] org = cipher.doFinal(Base64.getDecoder().decode(pass));
                    String ptext =  new String(org);
                    label2.setVisible(true);
                    label3.setVisible(true);
                    label3.setText(ptext);

                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Phone Number");
                    label2.setVisible(false);
                    label3.setVisible(false);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid Phone Number");
                label2.setVisible(false);
                label3.setVisible(false);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        button1 = new JButton();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setTitle("Forgot Password");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("Show Password");
        button1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- label1 ----
        label1.setText("Enter the Mobile Number :");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- textField1 ----
        textField1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("Password :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(6, 6, 6)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 398, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(button1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 168, Short.MAX_VALUE)
        );
        setSize(400, 200);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JButton button1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
