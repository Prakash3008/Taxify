import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sun Apr 12 20:14:11 IST 2020
 */



/**
 * @author Prakash
 */
public class driveprof extends JFrame {
    double totrat=0,rat1=0,ct=0;
    String rating;
    public driveprof(String tel) {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        initComponents();
        String mobile=tel;
        label3.setText(mobile);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "prakash2000");
            Statement smt = con.createStatement();
            String sel_query = "select * from Driver where phone= ?";
            PreparedStatement pst = con.prepareStatement(sel_query);
            pst.setString(1, mobile);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                if(rs.getString("phone").equals(mobile)){
                    label5.setText(rs.getString("name"));
                    label7.setText(rs.getString("email"));
                    label9.setText(rs.getString("age"));
                }
            }
            String rat_q = "select * from Trip_Details where dcontact=?";
            PreparedStatement pt = con.prepareStatement(rat_q);
            pt.setString(1,mobile);
            ResultSet rst = pt.executeQuery();
            while(rst.next()){

                rat1 = rat1+rst.getInt("Driver_Rating");
                ct=ct+1;
            }
            //rat1=Integer.parseInt(rat);
            totrat=(rat1/ct);
            double tot=Math.round(totrat*10)/10.0;
            rating=String.valueOf(tot);
            label15.setText(rating);
            con.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label6 = new JLabel();
        label8 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label13 = new JLabel();
        label9 = new JLabel();
        label7 = new JLabel();
        label5 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setTitle("Your Profile");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("My Profile");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        //---- label2 ----
        label2.setText("Name :");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label4 ----
        label4.setText("Mobile Number :");
        label4.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label6 ----
        label6.setText("Email-ID :");
        label6.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label8 ----
        label8.setText("Age :");
        label8.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label14 ----
        label14.setText("Rating :");
        label14.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //---- label15 ----
        label15.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label13 ----
        label13.setText("text");
        label13.setIcon(new ImageIcon("E:\\Sem4\\Java\\CAT3\\ratingstar.jpg"));

        //---- label9 ----
        label9.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label5 ----
        label5.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(129, 129, 129)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label14, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
                            .addGap(42, 42, 42)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(label15, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label7, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(label9, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(61, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(label13, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label15, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label14, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(58, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label6;
    private JLabel label8;
    private JLabel label14;
    private JLabel label15;
    private JLabel label13;
    private JLabel label9;
    private JLabel label7;
    private JLabel label5;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
