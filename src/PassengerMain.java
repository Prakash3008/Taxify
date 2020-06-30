import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sat Apr 11 14:59:32 IST 2020
 */



/**
 * @author Prakash
 */
public class PassengerMain extends JFrame {
    public String  mobile;
    public String dnum;
    public String p;
    public String d;
    public PassengerMain(String mob) {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        mobile = mob;
        button2.setVisible(false);
        spinner1.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        textField1.setVisible(false);

    }
    private void label5MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void menuItem3MouseClicked(MouseEvent e) {
    }

    private void button1MouseClicked(MouseEvent e) {
        p = comboBox1.getSelectedItem() + "";
        d = comboBox2.getSelectedItem() + "";
        if(p.equals(d))
        {
            JOptionPane.showMessageDialog(null,"Error. The Pickup Place and the Destination cannot be the same");
        }
        else{
            try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            String sel_query = "select * from CabBooking where dlocation= ?";
            PreparedStatement pst = con.prepareStatement(sel_query);
            pst.setString(1,p);
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getString("dlocation").equals(p)){
                    label4.setVisible(true);
                    String update_query="update CabBooking set pickup=?,destination=?,pcontact=? where dlocation=?";
                    PreparedStatement pt = con.prepareStatement(update_query);
                    pt.setString(1,p);
                    pt.setString(2,d);
                    pt.setString(3,mobile);
                    pt.setString(4,p);
                    dnum=rs.getString("dcontact");
                    pt.executeUpdate();
                    String del_query="delete from CabBooking where dcontact=? and pcontact=?";
                    PreparedStatement ps = con.prepareStatement(del_query);
                    ps.setString(1,dnum);
                    ps.setString(2,mobile);
                    ps.executeUpdate();
                    label4.setText("Your Trip has Started");
                    label5.setVisible(true);
                    label7.setVisible(true);
                    label6.setVisible(true);
                    label8.setVisible(true);
                    label6.setText(dnum);
                    button1.setEnabled(false);
                    button2.setVisible(true);
                    textField1.setVisible(true);
                    spinner1.setVisible(true);
                }
                else {
                    textField1.setVisible(false);
                    label5.setVisible(false);
                    label7.setVisible(false);
                    label8.setVisible(false);
                    label6.setVisible(false);
                    label4.setVisible(true);
                    label4.setText("Sorry! There are no nearby cabs");
                }}
            else {
                textField1.setVisible(false);
                label5.setVisible(false);
                label7.setVisible(false);
                label8.setVisible(false);
                label6.setVisible(false);
                label4.setVisible(true);
                label4.setText("Sorry! There are no nearby cabs");
                String ins_query= "insert into CabBooking (pickup,destination,pcontact) values (?, ?, ?)";
                PreparedStatement st = con.prepareStatement(ins_query);
                st.setString(1,p);
                st.setString(2,d);
                st.setString(3,mobile);
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
    }

    private void textField1FocusLost(FocusEvent e) {
        String x = textField1.getText();
        try {
            if (x.length() == 0) {
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

    private void button2MouseClicked(MouseEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            JOptionPane.showMessageDialog(null, "Your Trip has Successfully ended");
            String drt = spinner1.getValue() + "";
            int drat=Integer.parseInt(drt);
            Random rn = new Random();
            int range = (5 - 1) + 1;
            int prat = rn.nextInt(range) + 1;
            int ranget = (99999 - 10000) + 1;
            int tid = rn.nextInt(ranget) + 10000;
            int amt = Integer.parseInt(textField1.getText());
            java.util.Date date = new java.util.Date();
            long t = date.getTime();

            String insert_q = "insert into Trip_Details values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = con.prepareStatement(insert_q);
            pre.setInt(1,tid);
            pre.setString(2,dnum);
            pre.setString(3,mobile);
            pre.setString(4,p);
            pre.setString(5,d);
            pre.setInt(6,amt);
            pre.setInt(7,drat);
            pre.setInt(8,prat);
            pre.setDate(9,new java.sql.Date(t));
            pre.executeUpdate();
            con.close();
            button2.setVisible(false);
            textField1.setVisible(false);
            label5.setVisible(false);
            label6.setVisible(false);
            spinner1.setVisible(false);
            label4.setVisible(false);
            label8.setVisible(false);
            label7.setVisible(false);
            button1.setEnabled(true);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"You have successfully logged out");
        this.setVisible(false);
        //new HomePage().setVisible(true);
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        new passprofview(mobile).setVisible(true);
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        new passtrips(mobile).setVisible(true);
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
        label2 = new JLabel();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        comboBox2 = new JComboBox<>();
        button1 = new JButton();
        label4 = new JLabel();
        button2 = new JButton();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        textField1 = new JTextField();
        spinner1 = new JSpinner();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Book Your Ride");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Profile");
                menu1.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                //---- menuItem1 ----
                menuItem1.setText("View Profile");
                menuItem1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Your Trips");
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
        label1.setText("Book your ride now!");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        //---- label2 ----
        label2.setText("Pickup Place :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label3 ----
        label3.setText("Destination :");
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 18));

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

        //---- comboBox2 ----
        comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
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
        comboBox2.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("Search Cabs");
        button1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- button2 ----
        button2.setText("End Ride");
        button2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });

        //---- label5 ----
        label5.setText("Driver Details :");
        label5.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setText("Amount (in Rs.):");
        label7.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label8 ----
        label8.setText("Driver Rating (0-5) :");
        label8.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- textField1 ----
        textField1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField1FocusLost(e);
                textField1FocusLost(e);
            }
        });

        //---- spinner1 ----
        spinner1.setModel(new SpinnerNumberModel(1, 1, 5, 1));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboBox1)
                                .addComponent(comboBox2)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(94, 94, 94)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
                    .addGap(57, 57, 57))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap(43, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(label8, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(spinner1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                            .addGap(79, 79, 79)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
                    .addGap(70, 70, 70))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 159, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
                    .addGap(141, 141, 141))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                            .addGap(91, 91, 91))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(70, Short.MAX_VALUE))))
        );
        setSize(575, 540);
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
    private JLabel label2;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JButton button1;
    private JLabel label4;
    private JButton button2;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JTextField textField1;
    private JSpinner spinner1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
