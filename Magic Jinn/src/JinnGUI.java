import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;


public class JinnGUI extends JFrame {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private ImageIcon jLabel1Icon = new ImageIcon(getClass().getClassLoader().getResource("Jinn.png"));
  private JLabel lblSprechblase = new JLabel();
  private ImageIcon lblSprechblaseIcon = new ImageIcon(getClass().getClassLoader().getResource("bulle.gif"));
  private JLabel lblFrage = new JLabel();
  private JButton jaButton = new JButton();
  private JButton neinButton = new JButton();
  private JButton neuButton = new JButton();
  private JTextField ausgabe = new JTextField();
  private JButton btnSpeichern = new JButton();
  private JButton btnLaden = new JButton();
  private JButton btnZeigeBaum = new JButton();
  private JFileChooser jfco = new JFileChooser();
  private JFileChooser jfcs = new JFileChooser();
  private Jinn jinn = new Jinn();

  // Ende Attribute
  public JinnGUI(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 561;
    int frameHeight = 520;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jLabel1.setBounds(352, 64, 163, 305);
    jLabel1.setText("");
    jLabel1.setIcon(jLabel1Icon);
    cp.add(jLabel1);
    cp.setBackground(Color.WHITE);
    lblSprechblase.setBounds(80, 0, 342, 260);
    lblSprechblase.setText("");
    lblSprechblase.setIcon(lblSprechblaseIcon);
    cp.add(lblSprechblase);
    lblFrage.setBounds(120, 64, 206, 116);
    lblFrage.setText("Herzlich Willkommen!");
    lblFrage.setFont(new Font("Dialog", Font.BOLD, 12));
    lblFrage.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblFrage);

    jaButton.setBounds(136, 264, 139, 41);
    jaButton.setText("JA");
    jaButton.setMargin(new Insets(2, 2, 2, 2));
    jaButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jaButton_ActionPerformed(evt);
        }
      });
    jaButton.setBackground(new Color(0xC0C0C0));

    jaButton.setBorderPainted(false);
    jaButton.setVisible(false);
    cp.add(jaButton);

    neinButton.setBounds(136, 320, 139, 41);
    neinButton.setText("NEIN");
    neinButton.setMargin(new Insets(2, 2, 2, 2));
    neinButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          neinButton_ActionPerformed(evt);
        }
      });
    neinButton.setBackground(new Color(0xC0C0C0));

    neinButton.setBorderPainted(false);
    neinButton.setVisible(false);
    cp.add(neinButton);
    neuButton.setBounds(416, 384, 107, 49);
    neuButton.setText("neues Spiel");
    neuButton.setMargin(new Insets(2, 2, 2, 2));
    neuButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          neuButton_ActionPerformed(evt);
        }
      });

    neuButton.setBorderPainted(false);
    neuButton.setForeground(Color.BLACK);
    cp.add(neuButton);
    ausgabe.setBounds(8, 448, 390, 28);
    cp.add(ausgabe);
    btnSpeichern.setBounds(8, 416, 91, 25);
    btnSpeichern.setText("speichern");
    btnSpeichern.setMargin(new Insets(2, 2, 2, 2));
    btnSpeichern.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnSpeichern_ActionPerformed(evt);
        }
      });
    btnSpeichern.setBorderPainted(false);
    btnSpeichern.setForeground(Color.BLACK);
    cp.add(btnSpeichern);
    btnLaden.setBounds(104, 416, 91, 25);
    btnLaden.setText("laden");
    btnLaden.setMargin(new Insets(2, 2, 2, 2));
    btnLaden.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnLaden_ActionPerformed(evt);
        }
      });
    btnLaden.setBorderPainted(false);
    btnLaden.setForeground(Color.BLACK);
    cp.add(btnLaden);
    btnZeigeBaum.setBounds(440, 448, 73, 33);
    btnZeigeBaum.setText("Baum");
    btnZeigeBaum.setMargin(new Insets(2, 2, 2, 2));
    btnZeigeBaum.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnZeigeBaum_ActionPerformed(evt);
        }
      });
    btnZeigeBaum.setForeground(Color.BLACK);
    cp.add(btnZeigeBaum);
    // Ende Komponenten
    setVisible(true);
  } // end of public JinnGUI

  // Anfang Methoden
  public void jaButton_ActionPerformed(ActionEvent evt) {
    String meldung = jinn.frageMitJaBeantwortet();

    if (meldung.equals("Ich bin gut!")) {
      jaButton.setVisible(false);
      neinButton.setVisible(false);
    }

    lblFrage.setText(meldung);
  } // end of jaButton_ActionPerformed

  public void neinButton_ActionPerformed(ActionEvent evt) {
    String meldung = jinn.frageMitNeinBeantwortet();

    if (meldung != null) {
      lblFrage.setText(meldung);
    } else {
      String frage;

      do {
        frage = JOptionPane.showInputDialog("Neue Frage, die für dein Tier zutrifft, aber nicht für ein(e)" +
                                            jinn.getAktuell() + ".");
      } while (frage.equals("")); // end of do-while

      String lTier;

      do {
        lTier = JOptionPane.showInputDialog("Und gib dein Tier ein");
      } while (lTier.equals("")); // end of do-while

      jinn.erweitern(frage, lTier);
      jaButton.setVisible(false);
      neinButton.setVisible(false);
    } // end of if-else
  } // end of neinButton_ActionPerformed

  public void neuButton_ActionPerformed(ActionEvent evt) {
    lblFrage.setText(jinn.ersteFrage());

    //mache die Auswahlbuttons sichtbar
    jaButton.setVisible(true);
    neinButton.setVisible(true);
  } // end of neuButton_ActionPerformed

  public void btnZeigeBaum_ActionPerformed(ActionEvent evt) {
    BaumGui<String> gui = new BaumGui("Ratebaum", jinn.getRatebaum());
  } // end of btnZeigeBaum_ActionPerformed

  public void btnSpeichern_ActionPerformed(ActionEvent evt) {
    String dateiname = jfcsSaveFilename(); // Öffnen-Dialog starten

    if (dateiname != null) {
      try {
        FileWriter w = new FileWriter(dateiname);
        w.write("");
        w.close();
      } catch (IOException e) {
        System.err.println(e.toString());
      }
    }

    //TODO
  } // end of btnSpeichern_ActionPerformed

  public String speichern(BinaryTree<String> bintree) {
    return null;
  }

  public BinaryTree laden() {
    return null;
  }

  public void btnLaden_ActionPerformed(ActionEvent evt) {
    String dateiname = jfcoOpenFilename(); // Öffnen-Dialog starten
    File datei = new File(dateiname);
    String ratebauminhalt = "";

    try {
      FileReader reader = new FileReader(datei);
      BufferedReader puffer = new BufferedReader(reader);
      String s = puffer.readLine();

      while (s != null) {
        ratebauminhalt = ratebauminhalt + s + "\n"; // erst mal alles Komma-getrennt in einen String
        s = puffer.readLine();
      }

      reader.close();

      jinn.load(Arrays.asList(ratebauminhalt.split(",")));
    } catch (IOException e) {
      System.err.println(e.toString());
    }
  } // end of btnLaden_ActionPerformed

  private String jfcoOpenFilename() {
    jfco.setDialogTitle("Öffne Datei"); // neuer Öffnen-Dialog
    jfco.setCurrentDirectory(new File(System.getProperty("user.dir"))); // Verzeichnis auf aktuelles Projektverzeichnis setzen

    if (jfco.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { // Wenn ok gedrückt wurde

      return jfco.getSelectedFile().getPath(); // dann Dateinamen zurückgeben
    } else {
      return null;
    }
  }

  private String jfcsSaveFilename() {
    jfcs.setDialogTitle("Speichere Datei"); // neuer Speichern-Dialog
    jfcs.setCurrentDirectory(new File(System.getProperty("user.dir"))); // Verzeichnis auf aktuelles Projektverzeichnis setzen

    if (jfcs.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) { // Wenn ok gedrückt wurde

      return jfcs.getSelectedFile().getPath(); // dann Dateinamen zurückgeben
    } else {
      return null;
    }
  }

  // Ende Methoden
  public static void main(String[] args) {
    new JinnGUI("Magic Jinn");
  } // end of main
} // end of class JinnGUI
