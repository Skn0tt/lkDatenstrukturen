import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 18.04.2012
  * @author
  */
public class BaumGui extends JFrame {
  // Anfang Attribute
  private Canvas<String> canvas1 = new Canvas<>();
 
  private JLabel lblTitle = new JLabel();
  private JButton btnZeichnen = new JButton();
  private JButton btnPre = new JButton();
  private JButton btnIn = new JButton();
  private JButton btnPost = new JButton();
  private JLabel lblAusgabe = new JLabel();
  private JLabel jLabel1 = new JLabel();

  private JButton btnAnzahl = new JButton();
  private JButton btnHoehe = new JButton();
  private JLabel lblHoehe = new JLabel();
  private JLabel lblAnzahl = new JLabel();
  // Ende Attribute
  public BaumGui(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    int frameWidth = 993; 
    int frameHeight = 598;
    setSize(frameWidth, frameHeight);
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    canvas1.setBounds(8, 64, 961, 417);
    cp.add(canvas1);
    lblTitle.setBounds(24, 8, 947, 57);
    lblTitle.setText("Ahnenbaum");
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("Calibri", Font.BOLD, 36));
    cp.add(lblTitle);
    btnZeichnen.setBounds(8, 488, 161, 49);
    btnZeichnen.setText("Zeichnen");
    btnZeichnen.setMargin(new Insets(2, 2, 2, 2));
    btnZeichnen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnZeichnen_ActionPerformed(evt);
      }
    });
    btnZeichnen.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnZeichnen);
    
    // Ende Komponenten
    setVisible(true);
  } // end of public Test
  
  // Anfang Methoden
  public void btnZeichnen_ActionPerformed(ActionEvent evt) {
    BinaryTree<String> lutzieKaiser = new BinaryTree<>("Lutzie Kaiser");
    BinaryTree<String> matthiasKnott = new BinaryTree<>("Matthias Knott");
    BinaryTree<String> brigitteStall = new BinaryTree<>("Brigitte Stall");
    BinaryTree<String> opaStall = new BinaryTree<>("Opa Stall");
    BinaryTree<String> peterknott = new BinaryTree<>("Peter Knott", lutzieKaiser, matthiasKnott);
    BinaryTree<String> susannestall = new BinaryTree<>("Susanne Stall", brigitteStall, opaStall);
    BinaryTree<String> simonknott = new BinaryTree<>("Simon Knott", peterknott, susannestall);

    canvas1.zeichnen(simonknott);
  } // end of btnZeichnen_ActionPerformed
  
  
  // Ende Methoden
  public static void main(String[] args) {
    new BaumGui("BinaryTree");
  } // end of main
  
} // end of class Test
