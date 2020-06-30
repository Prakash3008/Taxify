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
 * Created by JFormDesigner on Tue Apr 14 15:31:58 IST 2020
 */



/**
 * @author Prakash
 */
public class fpassd extends JFrame {
    public fpassd() {
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

            PreparedStatement pst = con.prepareStatement("select * from Driver where phone = ?");
            pst.setString(1,mob);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getString("phone").equals(mob))
                {
                   String pass=rs.getString("password");
                    String initVector = "purisbkflaco89fh";
                    String key = "94hjiqpolzmnc34z";
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
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        textField1 = new JTextField();
        label3 = new JLabel();

        //======== this ========
        setTitle("Forgot Password");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Enter the Mobile Number :");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("Password :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("Show Password");
        button1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- textField1 ----
        textField1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(118, 118, 118)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(40, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(button1)
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        setSize(425, 195);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JTextField textField1;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
