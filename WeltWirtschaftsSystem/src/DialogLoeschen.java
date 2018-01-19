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
public class DialogLoeschen extends JDialog {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JButton ja = new JButton();
  private JButton nein = new JButton();
  private boolean loeschen = false;

  // Ende Attribute
  public DialogLoeschen(JFrame owner, String title, boolean modal) {
    // Dialog-Initialisierung
    super(owner, title, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 334;
    int frameHeight = 126;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jLabel1.setBounds(8, 24, 316, 20);
    jLabel1.setText("M�chten Sie den aktuellen Datensatz wirklich l�schen?");
    cp.add(jLabel1);
    ja.setBounds(64, 56, 75, 25);
    ja.setText("Ja");
    ja.setMargin(new Insets(2, 2, 2, 2));
    ja.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          ja_ActionPerformed(evt);
        }
      });
    cp.add(ja);
    nein.setBounds(184, 56, 75, 25);
    nein.setText("Nein");
    nein.setMargin(new Insets(2, 2, 2, 2));
    nein.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          nein_ActionPerformed(evt);
        }
      });
    cp.add(nein);
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
  }

  // Anfang Methoden
  public void ja_ActionPerformed(ActionEvent evt) {
    loeschen = true;
    dispose();
  }

  public void nein_ActionPerformed(ActionEvent evt) {
    loeschen = false;
    dispose();
  }

  public boolean getLoeschen() {
    return loeschen;
  }

  // Ende Methoden
}
