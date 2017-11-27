import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 09.10.2015
  * @author B. Reichelt
  */
public class GUIFaehre extends JFrame {
  // Anfang Attribute
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JTextField txtBahn1 = new JTextField("leer");
  private JTextField txtBahn2 = new JTextField("leer");
  private JTextField txtBahn3 = new JTextField("leer");
  private JTextField txtWartereihe = new JTextField("leer");
  private JButton btnStratASchritt = new JButton();
  private final int MAXLAENGE = 20;
  private Faehre faehre;
  private Stack<Double> wartereihe = new Stack();
  private JFileChooser jfco = new JFileChooser();
  private JLabel jLabel1 = new JLabel();
  private JLabel lblFrei1 = new JLabel();
  private JLabel lblFrei2 = new JLabel();
  private JLabel lblFrei3 = new JLabel();
  private boolean reihe1Erstes = true;
  private boolean reihe2Erstes = true;
  private boolean reihe3Erstes = true;
  private JTextArea txtAusgabe = new JTextArea("");
  private JScrollPane txtAusgabeScrollPane = new JScrollPane(txtAusgabe);
  private JLabel jLabel2 = new JLabel();

  //Menü
  private JMenuBar menu = new JMenuBar();
  private JMenu menueItem1 = new JMenu("Datei");
  private JMenuItem JMenuItem1 = new JMenuItem("Datei öffnen...");
  private JButton btnStratAkomplett = new JButton();
  private JButton btnStratBSchritt = new JButton();
  private JButton btnStratCSchritt = new JButton();
  private JButton btnStratBkomplett = new JButton();
  private JButton btnStratCkomplett = new JButton();

  // Ende Attribute
  public GUIFaehre(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 762;
    int frameHeight = 430;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jLabel4.setBounds(25, 64, 50, 30);
    jLabel4.setText("Reihe 1:");
    cp.add(jLabel4);
    jLabel5.setBounds(25, 112, 50, 30);
    jLabel5.setText("Reihe 2:");
    cp.add(jLabel5);
    jLabel6.setBounds(25, 160, 50, 30);
    jLabel6.setText("Reihe 3:");
    cp.add(jLabel6);
    jLabel7.setBounds(25, 200, 75, 30);
    jLabel7.setText("wartet:");
    cp.add(jLabel7);
    txtBahn1.setBounds(80, 64, 588, 30);
    txtBahn1.setEnabled(true);
    txtBahn1.setEditable(false);
    txtBahn1.setFont(new Font(".Dialog 9", Font.PLAIN, 14));
    cp.add(txtBahn1);
    txtBahn2.setBounds(80, 112, 588, 30);
    txtBahn2.setEnabled(true);
    //txtBahn2.setX(152);
    txtBahn2.setEditable(false);
    txtBahn2.setFont(new Font(".Dialog 9", Font.PLAIN, 14));
    cp.add(txtBahn2);
    txtBahn3.setBounds(80, 160, 588, 30);
    txtBahn3.setEnabled(true);
    //txtBahn3.setX(152);
    txtBahn3.setEditable(false);
    txtBahn3.setFont(new Font(".Dialog 9", Font.PLAIN, 14));
    cp.add(txtBahn3);
    txtWartereihe.setBounds(120, 200, 84, 30);
    txtWartereihe.setEnabled(true);
    //txtWartereihe.setX(152);
    txtWartereihe.setEditable(false);
    //txtWartereihe.setHeight(30);
    txtWartereihe.setFont(new Font(".Dialog 9", Font.PLAIN, 14));
    txtWartereihe.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(txtWartereihe);
    btnStratASchritt.setBounds(16, 240, 153, 25);
    btnStratASchritt.setText("Einweisen (Strategie A)");
    btnStratASchritt.setMargin(new Insets(2, 2, 2, 2));
    btnStratASchritt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStratASchritt_ActionPerformed(evt);
        }
      });
    cp.add(btnStratASchritt);
    jLabel1.setBounds(672, 40, 65, 25);
    jLabel1.setText("noch frei:");
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel1);
    lblFrei1.setBounds(680, 64, 51, 25);
    lblFrei1.setText("0");
    lblFrei1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblFrei1);
    lblFrei2.setBounds(680, 112, 51, 25);
    lblFrei2.setText("0");
    lblFrei2.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblFrei2);
    lblFrei3.setBounds(680, 160, 51, 25);
    lblFrei3.setText("0");
    lblFrei3.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblFrei3);
    txtAusgabeScrollPane.setBounds(336, 240, 401, 89);
    txtAusgabe.setFont(new Font("Dialog", Font.PLAIN, 14));
    txtAusgabe.setEditable(true);
    cp.add(txtAusgabeScrollPane);
    jLabel2.setBounds(8, 8, 731, 37);
    jLabel2.setText("Autofähre");
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setFont(new Font("Dialog", Font.BOLD, 28));
    cp.add(jLabel2);
    btnStratAkomplett.setBounds(176, 240, 153, 25);
    btnStratAkomplett.setText("Fähre füllen (Strategie A)");
    btnStratAkomplett.setMargin(new Insets(2, 2, 2, 2));
    btnStratAkomplett.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStratAkomplett_ActionPerformed(evt);
        }
      });
    cp.add(btnStratAkomplett);
    btnStratBSchritt.setBounds(16, 272, 153, 25);
    btnStratBSchritt.setText("Einweisen (Strategie B)");
    btnStratBSchritt.setMargin(new Insets(2, 2, 2, 2));
    btnStratBSchritt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStratBSchritt_ActionPerformed(evt);
        }
      });
    cp.add(btnStratBSchritt);
    btnStratCSchritt.setBounds(16, 304, 153, 25);
    btnStratCSchritt.setText("Einweisen (Strategie C)");
    btnStratCSchritt.setMargin(new Insets(2, 2, 2, 2));
    btnStratCSchritt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStratCSchritt_ActionPerformed(evt);
        }
      });
    cp.add(btnStratCSchritt);
    btnStratBkomplett.setBounds(176, 272, 153, 25);
    btnStratBkomplett.setText("Fähre füllen (Strategie B)");
    btnStratBkomplett.setMargin(new Insets(2, 2, 2, 2));
    btnStratBkomplett.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStratBkomplett_ActionPerformed(evt);
        }
      });
    cp.add(btnStratBkomplett);
    btnStratCkomplett.setBounds(176, 304, 153, 25);
    btnStratCkomplett.setText("Fähre füllen (Strategie C)");
    btnStratCkomplett.setMargin(new Insets(2, 2, 2, 2));
    btnStratCkomplett.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStratCkomplett_ActionPerformed(evt);
        }
      });
    cp.add(btnStratCkomplett);
    // Ende Komponenten
    setVisible(true);

    faehre = new Faehre("Beispiel1.txt", MAXLAENGE);
    txtAusgabe.setText(txtAusgabe.getText() + "Datei: " + "Beispiel1.txt" +
                       "\n");

    if (faehre.erstesWartendesAuto() != null) {
      txtWartereihe.setText("| " + faehre.erstesWartendesAuto().getLaenge() +
                            " m | ");
    } else {
      txtWartereihe.setText("leer!");
    } // end of if-else

    //Menü
    setJMenuBar(menu);
    menu.add(menueItem1);
    menueItem1.add(JMenuItem1);
    JMenuItem1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          JMenuItem1_ActionPerformed(evt);
        }
      });
    menueItem1.add(JMenuItem1);
  } // end of public GUIFaehre

  // Anfang Methoden
  public static void main(String[] args) {
    new GUIFaehre("GUIFaehre");
  } // end of main

  //Menü: Aktion ausführen
  public void JMenuItem1_ActionPerformed(ActionEvent evt) {
    String dateiname = jfcoOpenFilename();
    faehre = new Faehre(dateiname, MAXLAENGE);
    txtAusgabe.setText(txtAusgabe.getText() + "Datei: " + dateiname + "\n");
    btnStratASchritt.setEnabled(true);
    btnStratAkomplett.setEnabled(true);
    btnStratBSchritt.setEnabled(true);
    btnStratBkomplett.setEnabled(true);
    btnStratCSchritt.setEnabled(true);
    btnStratCkomplett.setEnabled(true);

    if (faehre.erstesWartendesAuto() != null) {
      txtWartereihe.setText("| " + faehre.erstesWartendesAuto().getLaenge() +
                            " m | ");
    } else {
      txtWartereihe.setText("leer!");
    } // end of if-else

    txtBahn1.setText("leer");
    txtBahn2.setText("leer");
    txtBahn3.setText("leer");
    txtAusgabe.setText("");
  }

  private String jfcoOpenFilename() {
    jfco.setDialogTitle("Öffne Datei"); // neuer Öffnen-Dialog
    jfco.setCurrentDirectory(new File(System.getProperty("user.dir"))); // Verzeichnis auf aktuelles Projektverzeichnis setzen

    if (jfco.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { // Wenn ok gedrückt wurde

      return jfco.getSelectedFile().getPath(); // dann Dateinamen zurückgeben
    } else {
      return null;
    }
  } // end of btnDateiLaden_ActionPerformed

  public void aktualisiereReihe(int pReihe) {
    //Wartereihe aktualisieren
    if (faehre.erstesWartendesAuto() != null) {
      txtWartereihe.setText("| " + faehre.erstesWartendesAuto().getLaenge() +
                            " m | ");
    } else {
      txtWartereihe.setText("leer!");
    } // end of if-else

    switch (pReihe) {
      case 1:

        if (txtBahn1.getText().equals("leer")) {
          txtBahn1.setText("| " + faehre.getTopReihe1().getLaenge() + " m | ");
        } else {
          txtBahn1.setText(txtBahn1.getText() + "| 0.3 m | " + "| " +
                           faehre.getTopReihe1().getLaenge() + " m | ");
        } // end of if-else

        break;

      case 2:

        if (txtBahn2.getText().equals("leer")) {
          txtBahn2.setText("| " + faehre.getTopReihe2().getLaenge() + " m | ");
        } else {
          txtBahn2.setText(txtBahn2.getText() + "| 0.3 m | " + "| " +
                           faehre.getTopReihe2().getLaenge() + " m | ");
        } // end of if-else

        break;

      case 3:

        if (txtBahn3.getText().equals("leer")) {
          txtBahn3.setText("| " + faehre.getTopReihe3().getLaenge() + " m | ");
        } else {
          txtBahn3.setText(txtBahn3.getText() + "| 0.3 m | " + "| " +
                           faehre.getTopReihe3().getLaenge() + " m | ");
        } // end of if-else

        break;

      default:

        if (pReihe == 0) {
          if (faehre.erstesWartendesAuto() != null) {
            txtAusgabe.setText(txtAusgabe.getText() +
                               "Fähre kann nicht mehr beladen werden!");
            txtAusgabe.setText(txtAusgabe.getText() + "\nRestlänge: " +
                               faehre.restlaenge());
          } // end of if
          else {
            txtAusgabe.setText("Warteschlange leer!");
          } // end of if-else

          btnStratASchritt.setEnabled(false);
          btnStratAkomplett.setEnabled(false);
          btnStratBSchritt.setEnabled(false);
          btnStratBkomplett.setEnabled(false);
          btnStratCSchritt.setEnabled(false);
          btnStratCkomplett.setEnabled(false);
        } // end of if
    } // end of switch

    lblFrei1.setText((Math.round(faehre.getFreiReihe1() * 100) / 100.) + " m");
    lblFrei2.setText((Math.round(faehre.getFreiReihe2() * 100) / 100.) + " m");
    lblFrei3.setText((Math.round(faehre.getFreiReihe3() * 100) / 100.) + " m");
  }

  public void btnStratASchritt_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    int reihe = faehre.strategieA();
    aktualisiereReihe(reihe);
    //andere Buttons ausblenden
    btnStratBSchritt.setEnabled(false);
    btnStratBkomplett.setEnabled(false);
    btnStratCSchritt.setEnabled(false);
    btnStratCkomplett.setEnabled(false);
  } // end of btnStratASchritt_ActionPerformed

  public void btnStratAkomplett_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    txtAusgabe.setText(txtAusgabe.getText() + "Strategie A\n");

    int reihe = 0;

    do {
      reihe = faehre.strategieA();
      aktualisiereReihe(reihe);
    } while (reihe != 0); // end of do-while
  } // end of btnStratAkomplett_ActionPerformed

  public void btnStratBSchritt_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen

    //andere Buttons ausblenden
    btnStratASchritt.setEnabled(false);
    btnStratAkomplett.setEnabled(false);
    btnStratCSchritt.setEnabled(false);
    btnStratCkomplett.setEnabled(false);
  } // end of btnStratBSchritt_ActionPerformed

  public void btnStratCSchritt_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen

    //andere Buttons ausblenden
    btnStratASchritt.setEnabled(false);
    btnStratAkomplett.setEnabled(false);
    btnStratBSchritt.setEnabled(false);
    btnStratBkomplett.setEnabled(false);
  } // end of btnStratCSchritt_ActionPerformed

  public void btnStratBkomplett_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    txtAusgabe.setText(txtAusgabe.getText() + "Strategie B\n");
  } // end of btnStratCkomplett_ActionPerformed

  public void btnStratCkomplett_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen 
    txtAusgabe.setText(txtAusgabe.getText() + "Strategie C\n");
  } // end of btnStratCkomplett_ActionPerformed

  // Ende Methoden
} // end of class GUIFaehre
