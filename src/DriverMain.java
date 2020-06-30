import jdk.nashorn.internal.runtime.ECMAException;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.*;

/*
 * Created by JFormDesigner on Sat Apr 11 21:42:52 IST 2020
 */



/**
 * @author Prakash
 */
public class DriverMain extends JFrame {
    public String tel;
    public String pmob;
    public String pdes;
    public String loc;
    public DriverMain(String mob) {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        tel=mob;
        button2.setVisible(false);
        textField1.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        spinner1.setVisible(false);
        label3.setVisible(false);
    }


    private void button1MouseClicked(MouseEvent e) {
        loc = comboBox1.getSelectedItem() + "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            Statement smt = con.createStatement();
            String sel_query = "select * from CabBooking where pickup= ?";
            PreparedStatement pst = con.prepareStatement(sel_query);
            pst.setString(1,loc);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getString("pickup").equals(loc)){
                    label2.setVisible(true);
                    String update_query="update CabBooking set dcontact=?,dlocation=? where pickup=?";
                    PreparedStatement pt = con.prepareStatement(update_query);
                    pt.setString(1,tel);
                    pt.setString(2,loc);
                    pt.setString(3,loc);
                    pmob=rs.getString("pcontact");
                    pdes=rs.getString("destination");
                    pt.executeUpdate();
                    String del_query="delete from CabBooking where dcontact=? and pcontact=?";
                    PreparedStatement p = con.prepareStatement(del_query);
                    p.setString(1,tel);
                    p.setString(2,pmob);
                    p.executeUpdate();
                    label2.setText("Your Trip has Started");
                    label3.setVisible(true);
                    label4.setVisible(true);
                    label4.setText(pmob);
                    button1.setEnabled(false);
                    button2.setVisible(true);
                    textField1.setVisible(true);
                    label5.setVisible(true);
                    label6.setVisible(true);
                    spinner1.setVisible(true);
                }
                else {
                    label4.setVisible(false);
                    label3.setVisible(false);
                    label2.setVisible(true);
                    label2.setText("Sorry! There are no nearby rides");
                }}
                else {
                    textField1.setVisible(false);
                    label5.setVisible(false);
                    label4.setVisible(false);
                    label3.setVisible(false);
                    label2.setVisible(true);
                    label2.setText("Sorry! There are no nearby rides");
                    String ins_query= "insert into CabBooking (dcontact,dlocation) values (?, ?)";
                    PreparedStatement st = con.prepareStatement(ins_query);
                    st.setString(1,tel);
                    st.setString(2,loc);
                    st.executeUpdate();
                }

            con.close();
            //JOptionPane.showMessageDialog(null,"Please wait");
            //this.setVisible(false);
            //new PassengerLogin().setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void button2MouseClicked(MouseEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            Statement smt = con.createStatement();
            JOptionPane.showMessageDialog(null, "Your Trip has Successfully ended");
            String pra = spinner1.getValue() + "";
            int prat = Integer.parseInt(pra);
            Random rn = new Random();
            int range = (5 - 1) + 1;
            int randrat = rn.nextInt(range) + 1;
            int ranget = (99999 - 10000) + 1;
            int randt = rn.nextInt(ranget) + 10000;
            String amt = textField1.getText();
            int fare=Integer.parseInt(amt);
            Date date = Calendar.getInstance().getTime();
            long t = date.getTime();

            String insert_q = "insert into Trip_Details values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = con.prepareStatement(insert_q);
            pre.setInt(1,randt);
            pre.setString(2,tel);
            pre.setString(3,pmob);
            pre.setString(4,loc);
            pre.setString(5,pdes);
            pre.setInt(6,fare);
            pre.setInt(7,randrat);
            pre.setInt(8,prat);
            pre.setTimestamp(9,new java.sql.Timestamp(t));
            pre.executeUpdate();
            con.close();
            button2.setVisible(false);
            textField1.setVisible(false);
            label5.setVisible(false);
            label6.setVisible(false);
            spinner1.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            label4.setVisible(false);
            button1.setEnabled(true);
        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }

    private void textField1FocusLost(FocusEvent e) {
        String x = textField1.getText();

        try {
            if(x.length() == 0){
                throw new Exception("It should contain only numbers");
            }
            for (int i = 0; i < x.length(); i++) {
                if (!(Character.isDigit(x.charAt(i)))) {
                    textField1.setText("");
                    throw new Exception("It should contain only numbers");
                }
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "It should contain only numbers");
        }
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
            new driveprof(tel).setVisible(true);
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        new drivertrips(tel).setVisible(true);
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JOptionPane.showMessageDialog(null,"You have successfully logged out");
        //new HomePage().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();
        button1 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button2 = new JButton();
        label5 = new JLabel();
        textField1 = new JTextField();
        spinner1 = new JSpinner();
        label6 = new JLabel();

        //======== this ========
        setTitle("Driver ");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Driver Profile");
                menu1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                //---- menuItem1 ----
                menuItem1.setText("View Profile");
                menuItem1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Your Rides");
                menuItem2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("Logout");
                menuItem3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("Enter Current Location :");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Gandhipuram",
            "Isha",
            "RS Puram",
            "Ukkadam",
            "Hopes College",
            "Periyanaiyekapalayam",
            "Singanallur",
            "Cheran Ma Nagar",
            "Vilankurichi",
            "PSG College",
            "CIT College",
            "Perur",
            "Sitra Airport",
            "Cross Cut Road",
            "100 Feet Road",
            "Gandhi Ma Nagar",
            "Ganapathy",
            "Sulur",
            "Kovaiputhur",
            "Sai Baba Colony",
            "Ramanathapuram"
        }));
        comboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("Get Rides");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- label3 ----
        label3.setText("Passenger Details :");
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- button2 ----
        button2.setText("End Trip");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });

        //---- label5 ----
        label5.setText("Amount (in Rs.):");
        label5.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField1FocusLost(e);
            }
        });

        //---- spinner1 ----
        spinner1.setModel(new SpinnerNumberModel(1, 1, 5, 1));

        //---- label6 ----
        label6.setText("Passenger Rating (0-5) :");
        label6.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label6, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(169, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addComponent(spinner1, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(76, 76, 76)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
                    .addGap(49, 49, 49))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(115, 115, 115)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGap(35, 35, 35)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(15, 15, 15))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32))))
        );
        setSize(540, 495);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JLabel label1;
    private JComboBox<String> comboBox1;
    private JButton button1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button2;
    private JLabel label5;
    private JTextField textField1;
    private JSpinner spinner1;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
