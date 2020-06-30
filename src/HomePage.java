import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Wed Apr 01 15:49:58 IST 2020
 */



/**
 * @author Prakash
 */
public class HomePage extends JFrame {
    public HomePage() {
        initComponents();
        this.setVisible(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    private void thisMouseClicked(MouseEvent e) {
        // TODO add your code here
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
    }

    private void label7MouseClicked(MouseEvent e) {
        //this.setVisible(false);
        new LoginDriver().setVisible(true);
    }

    private void label8MouseClicked(MouseEvent e) {
        //this.setVisible(false);
        new PassengerLogin().setVisible(true);
    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prakash
        label7 = new JLabel();
        label8 = new JLabel();
        label1 = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setTitle("Taxify");
        setIconImage(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifylogo.png").getImage());
        setBackground(UIManager.getColor("EditorPane.selectionBackground"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label7 ----
        label7.setText("Driver");
        label7.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        label7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label7MouseClicked(e);
            }
        });

        //---- label8 ----
        label8.setText("Passenger");
        label8.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        label8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label8MouseClicked(e);
            }
        });

        //---- label1 ----
        label1.setIcon(new ImageIcon("E:\\Sem4\\Java\\CAT3\\taxifyfin1.jpg"));

        //---- label3 ----
        label3.setText("Welcome to Taxify!");
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 48));

        //---- label2 ----
        label2.setText("Book a City Taxi to your destination in town");
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(168, 168, 168)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                        .addComponent(label1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 43, Short.MAX_VALUE))
        );
        setSize(790, 375);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prakash
    private JLabel label7;
    private JLabel label8;
    private JLabel label1;
    private JLabel label3;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

