import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Sun Apr 12 16:33:01 IST 2020
 */



/**
 * @author Prakash
 */
public class passtrips extends JFrame {
    public passtrips(String mobile) {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        String mob=mobile;
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
                if(rs.getString("pcontact").equals(mob)){
                DefaultTableModel mod = (DefaultTableModel)table1.getModel();
                Object[] row= {rs.getInt("tripid"),rs.getString("Pickup"),rs.getString("Destination"),rs.getInt("Amount"),rs.getDate("Date_of_Travel")};
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
        setTitle("Your Trips");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                },
                new String[] {
                    "Trip ID", "Pickup Place", "Destination", "Amount", "Trip Date"
                }
            ));
            table1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            scrollPane1.setViewportView(table1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE))
        );
        setSize(600, 365);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
