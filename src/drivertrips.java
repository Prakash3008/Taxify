import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Sun Apr 12 20:27:24 IST 2020
 */



/**
 * @author Prakash
 */
public class drivertrips extends JFrame {
    public drivertrips(String tel) {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        String mobile = tel;
        try {
            DefaultTableModel dt = (DefaultTableModel) table1.getModel();
            int rcount = dt.getRowCount();
            for (int i = rcount - 1; i >= 0; i--) {
                dt.removeRow(i);
            }
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","prakash2000");
            String sel_query = "select * from Trip_Details";
            PreparedStatement pst = con.prepareStatement(sel_query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getString("dcontact").equals(mobile)){
                    DefaultTableModel mod = (DefaultTableModel)table1.getModel();
                    Object[] row= {String.valueOf(rs.getInt("tripid")),rs.getString("Pickup"),rs.getString("Destination"),String.valueOf(rs.getInt("Amount")),rs.getDate("Date_of_Travel")};
                    mod.addRow(row);
                }}
            con.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setTitle("My Rides");
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                },
                new String[] {
                    "Trip ID", "Pickup Place", "Destination", "Amount", "Date of Travel"
                }
            ));
            scrollPane1.setViewportView(table1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
        );
        setSize(555, 475);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
