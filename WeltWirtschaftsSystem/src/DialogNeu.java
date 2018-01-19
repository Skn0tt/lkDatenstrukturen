import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *

  * Beschreibung
  *
  * @version 1.0 vom 10.03.2012
  * @author
  */
public class DialogNeu extends JDialog {
  // Anfang Attribute
  private JLabel lblHinzufuegen = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField111 = new JTextField();
  private JTextField jTextField112 = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel21 = new JLabel();
  private JLabel jLabel211 = new JLabel();
  private JLabel jLabel212 = new JLabel();
  private JButton btnHinzufuegen = new JButton();
  private JFileChooser bildWaehlen = new JFileChooser();
  private JButton btnBildWaehlen = new JButton();
  private JLabel jLabel3 = new JLabel();
  private Warentyp ware;

  // Ende Attribute
  public DialogNeu(JFrame owner, String title, boolean modal) {
    // Dialog-Initialisierung
    super(owner, title, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 284;
    int frameHeight = 243;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    lblHinzufuegen.setBounds(8, 8, 110, 20);
    lblHinzufuegen.setText("Ware hinzufügen:");
    cp.add(lblHinzufuegen);
    jTextField1.setBounds(80, 40, 118, 20);
    jTextField1.setText("");
    cp.add(jTextField1);
    jLabel2.setBounds(16, 40, 54, 20);
    jLabel2.setText("Name");
    cp.add(jLabel2);
    jLabel21.setBounds(16, 72, 54, 20);
    jLabel21.setText("Bild");
    cp.add(jLabel21);
    jLabel211.setBounds(16, 112, 54, 20);
    jLabel211.setText("EAN");
    cp.add(jLabel211);
    jLabel212.setBounds(16, 144, 54, 20);
    jLabel212.setText("Preis");
    cp.add(jLabel212);
    btnHinzufuegen.setBounds(32, 176, 139, 25);
    btnHinzufuegen.setText("Ware hinzufügen");
    btnHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    btnHinzufuegen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnHinzufuegen_ActionPerformed(evt);
        }
      });
    cp.add(btnHinzufuegen);
    btnBildWaehlen.setBounds(80, 72, 99, 25);
    btnBildWaehlen.setText("Bild wählen ...");
    btnBildWaehlen.setMargin(new Insets(2, 2, 2, 2));
    btnBildWaehlen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnBildWaehlen_ActionPerformed(evt);
        }
      });
    cp.add(btnBildWaehlen);
    jTextField111.setBounds(80, 112, 118, 20);
    jTextField112.setBounds(80, 144, 118, 20);
    jLabel3.setBounds(192, 72, 70, 20);
    jLabel3.setText("");
    cp.add(jLabel3);
    cp.add(jTextField111);
    cp.add(jTextField112);
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
  }

  // Anfang Methoden
  public Warentyp getWare() {
    return ware;
  }

  public void btnHinzufuegen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    ware = new Warentyp(jTextField1.getText(), jLabel3.getText(),
                        jTextField111.getText(),
                        Double.parseDouble(jTextField112.getText()));

    dispose();
  }

  public String bildWaehlenOpenFilename() {
    bildWaehlen.setDialogTitle("Öffne Bilddatei");

    if (bildWaehlen.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      return bildWaehlen.getSelectedFile().getPath();
    } else {
      return null;
    }
  }

  public void btnBildWaehlen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    jLabel3.setText(bildWaehlenOpenFilename());
  }

  // Ende Methoden
}
