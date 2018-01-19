import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 10.03.2012
  * @author
  */
public class HilfeDialog extends JDialog {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JLabel lblBReichelt2018 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JButton jButton1 = new JButton();

  // Ende Attribute
  public HilfeDialog(JFrame owner, String title, boolean modal) {
    // Dialog-Initialisierung
    super(owner, title, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 270;
    int frameHeight = 181;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jLabel1.setBounds(32, 16, 181, 26);
    jLabel1.setText("Warenverwaltungssystem");
    jLabel1.setFont(new Font("Arial Narrow", Font.BOLD, 18));
    cp.add(jLabel1);
    lblBReichelt2018.setBounds(32, 88, 195, 20);
    lblBReichelt2018.setText("(c) B. Reichelt 2018");
    cp.add(lblBReichelt2018);
    jLabel3.setBounds(32, 56, 192, 20);
    jLabel3.setText("System zur Verwaltung von Waren.");
    jLabel3.setFont(new Font("Arial", Font.PLAIN, 12));
    cp.add(jLabel3);
    jButton1.setBounds(88, 120, 75, 25);
    jButton1.setText("Schließen");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jButton1_ActionPerformed(evt);
        }
      });
    cp.add(jButton1);
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
  }

  // Anfang Methoden
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    dispose();
  }

  // Ende Methoden
}
