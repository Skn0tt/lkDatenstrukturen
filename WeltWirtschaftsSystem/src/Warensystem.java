import java.awt.*;
import java.awt.event.*;

import java.io.*;

import java.net.*;

import java.text.*;

import java.util.*;

import javax.swing.*;


public class Warensystem extends JFrame {
  // Anfang Attribute
  private TextField txtAnzeigen = new TextField();
  private Button btnAnzeigen = new Button();
  private Label label2 = new Label();
  private Label label3 = new Label();
  private Button btnFirst = new Button();
  private Button btnPrev = new Button();
  private Button btnNext = new Button();
  private Button btnLast = new Button();
  private JLabel lblImage = new JLabel();
  private JLabel lblEanCode = new JLabel();
  private DecimalFormat f = new DecimalFormat("#0.00");
  private Label label1 = new Label();
  private URL url;
  private Button btnLoeschen = new Button();
  private JMenuBar menu = new JMenuBar();
  private JMenu bearbeiten = new JMenu("Bearbeiten");
  private JMenuItem BearbeitenJMenuItem1 = new JMenuItem("Rückgängig");
  private JMenu datei = new JMenu("Datei");
  private JMenuItem DateiJMenuItem1 = new JMenuItem("Neu...");
  private JMenuItem DateiJMenuItem2 = new JMenuItem("Beenden");
  private JMenu hilfe = new JMenu("Hilfe");
  private JMenuItem HilfeJMenuItem1 = new JMenuItem("Über");
  private ImageIcon eanImage;
  private Stack<Warentyp> geloescht = new Stack<Warentyp>();
  private SortedList<Warentyp> warenliste = new SortedList<Warentyp>();
  ClassLoader classLoader = getClass().getClassLoader();

  // Ende Attribute
  public Warensystem(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 272;
    int frameHeight = 591;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    txtAnzeigen.setBounds(16, 56, 166, 30);
    txtAnzeigen.setForeground(Color.BLACK);
    cp.add(txtAnzeigen);
    btnAnzeigen.setBounds(192, 56, 59, 30);
    btnAnzeigen.setLabel("anzeigen");
    btnAnzeigen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnAnzeigen_ActionPerformed(evt);
        }
      });
    cp.add(btnAnzeigen);
    label2.setBounds(32, 288, 198, 28);
    label2.setText("");
    label2.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(label2);
    label3.setBounds(88, 320, 86, 20);
    label3.setText("");
    cp.add(label3);
    btnFirst.setBounds(32, 496, 40, 30);
    btnFirst.setLabel("<<");
    btnFirst.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnFirst_ActionPerformed(evt);
        }
      });
    btnFirst.setFont(new Font("Dialog", Font.PLAIN, 20));
    cp.add(btnFirst);
    btnPrev.setBounds(80, 496, 40, 30);
    btnPrev.setLabel("<");
    btnPrev.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnPrev_ActionPerformed(evt);
        }
      });
    btnPrev.setFont(new Font("Dialog", Font.PLAIN, 20));
    cp.add(btnPrev);
    btnNext.setBounds(144, 496, 40, 30);
    btnNext.setLabel(">");
    btnNext.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnNext_ActionPerformed(evt);
        }
      });
    btnNext.setFont(new Font("Dialog", Font.PLAIN, 20));
    cp.add(btnNext);
    btnLast.setBounds(192, 496, 40, 30);
    btnLast.setLabel(">>");
    btnLast.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnLast_ActionPerformed(evt);
        }
      });
    btnLast.setFont(new Font("Dialog", Font.PLAIN, 20));
    cp.add(btnLast);
    lblImage.setBounds(32, 88, 200, 200);
    lblImage.setText("");
    lblImage.setBackground(Color.WHITE);
    lblImage.setOpaque(true);
    lblImage.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblImage);
    lblEanCode.setBounds(16, 344, 232, 104);
    lblEanCode.setText("");
    lblEanCode.setBackground(Color.WHITE);
    lblEanCode.setOpaque(true);
    lblEanCode.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblEanCode);
    label1.setBounds(16, 16, 238, 28);
    label1.setText("Warenwirtschaftssystem");
    label1.setFont(new Font("Dialog", Font.BOLD, 20));
    label1.setAlignment(Label.CENTER);
    cp.add(label1);
    btnLoeschen.setBounds(96, 456, 59, 30);
    btnLoeschen.setLabel("loeschen");
    btnLoeschen.addActionListener(this::btnLoeschen_ActionPerformed);
    cp.add(btnLoeschen);
    setJMenuBar(menu);
    menu.add(datei);
    menu.add(bearbeiten);
    menu.add(hilfe);
    DateiJMenuItem1.addActionListener(this::DateiJMenuItem1_ActionPerformed);
    datei.add(DateiJMenuItem1);
    DateiJMenuItem2.addActionListener(this::DateiJMenuItem2_ActionPerformed);
    datei.add(DateiJMenuItem2);
    BearbeitenJMenuItem1.addActionListener(this::BearbeitenJMenuItem1_ActionPerformed);
    bearbeiten.add(BearbeitenJMenuItem1);
    HilfeJMenuItem1.addActionListener(this::HilfeJMenuItem1_ActionPerformed);
    hilfe.add(HilfeJMenuItem1);

    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
          Warensystem_WindowClosing(evt);
        }
      });
    // Ende Komponenten
    setVisible(true);

    listeFuellen();

    warenliste.toFirst();

    if (warenliste.hasAccess()) {
      refresh();
    } else {
      label2.setText("Warenliste leer.");
      label3.setText("");
      lblImage.setIcon(new ImageIcon(getClass().getResource("images/error.gif")));
      lblEanCode.setIcon(new ImageIcon(""));
    }
  }

  // Anfang Methoden
  public void refresh() {
    if (warenliste.hasAccess()) {
      Warentyp ware = warenliste.getContent();
      label2.setText(ware.getName());
      label3.setText("Preis: " + f.format(ware.getPreis()) + " Euro");
      lblImage.setIcon(new ImageIcon(ware.getBild()));
      EANgenerator(ware.getID());
    } else { showError(); }
  }

  public void EANgenerator(String ean) {
    try {
      url = new URL("https://barcode.tec-it.com/barcode.ashx?data=" + ean +
                    "&code=EAN13&dpi=96&dataseparator=");

      //ean + "&code=EAN13&dpi=96&dataseparator=");
    } catch (Exception e) {
    }

    eanImage = new ImageIcon(url);
    lblEanCode.setIcon(eanImage);
  }

  public void btnAnzeigen_ActionPerformed(ActionEvent evt) {
    if (!txtAnzeigen.getText().equals("") &&
          !txtAnzeigen.getText().equals("bitte EAN hier eingeben...")) {
      warenliste.getByID(txtAnzeigen.getText());
      refresh();
    } else {
      txtAnzeigen.setText("bitte EAN hier eingeben...");
    }
  }

  public void btnFirst_ActionPerformed(ActionEvent evt) {
    warenliste.toFirst();

    if (warenliste.hasAccess()) {
      refresh();
    } else {
      showError();
    }
  }

  public void btnPrev_ActionPerformed(ActionEvent evt) {
    warenliste.previous();

    if (warenliste.hasAccess()) {
      refresh();
    }
  }

  public void btnNext_ActionPerformed(ActionEvent evt) {
    if (warenliste.hasAccess()) {
      warenliste.next();

      if (!warenliste.hasAccess()) {
        warenliste.toLast();
      } else {
        refresh();
      }
    }
  }

  public void btnLast_ActionPerformed(ActionEvent evt) {
    warenliste.toLast();

    if (warenliste.hasAccess()) {
      refresh();
    } else {
      showError();
    }
  }

  public void showError() {
    label2.setText("Warenliste leer.");
    label3.setText("");
    lblImage.setIcon(new ImageIcon(getClass().getResource("images/error.gif")));
    lblEanCode.setIcon(new ImageIcon(""));
  }

  public void btnLoeschen_ActionPerformed(ActionEvent evt) {
    DialogLoeschen dialog = new DialogLoeschen(this, "Wirklich löschen?", true);
    boolean loeschen = dialog.getLoeschen();

    if (loeschen) {
      geloescht.push(warenliste.getContent());
      zeileLoeschen(warenliste.getContent().getID());
      warenliste.remove();
      refresh();
    }
  }

  public void DateiJMenuItem1_ActionPerformed(ActionEvent evt) {
    DialogNeu neu = new DialogNeu(this, "Neuer Warentyp", true);
    Warentyp ware = neu.getWare();

    if (ware != null) {
      // Check if Ware already exists
      warenliste.getByID(ware.getID());
      if (warenliste.hasAccess()) { return; }

      // Inser Ware
      warenliste.insert(ware);
      zeileHinzufuegen(ware);
      refresh();
    }
  }

  public void DateiJMenuItem2_ActionPerformed(ActionEvent evt) {
    System.exit(0);
  }

  public void BearbeitenJMenuItem1_ActionPerformed(ActionEvent evt) {
    Warentyp ware = geloescht.top();
    warenliste.append(ware);
    zeileHinzufuegen(ware);
    geloescht.pop();
    refresh();
  }

  public void HilfeJMenuItem1_ActionPerformed(ActionEvent evt) {
    HilfeDialog hilfe = new HilfeDialog(this, "Über...", true);
  }

  public void zeileLoeschen(String ean) {
    try {
      File original = new File("Artikel.txt");
      File kopie = new File("Kopie.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(original)));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(kopie)));
      int counter = 0;
      String line;

      while ((line = br.readLine()) != null) {
        String[] text = line.split(";");

        if (text[0].equals("") || !text[2].equals(ean)) {
          bw.write(line);
          bw.newLine();
        }

        counter++;
      }

      bw.close();
      br.close();
      original.delete();
      kopie.renameTo(original);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void zeileHinzufuegen(Warentyp ware) {
    File ausgabe = new File("Artikel.txt");

    try {
      FileWriter writer = new FileWriter(ausgabe, true);
      writer.write(ware.getName() + ";" + ware.getBild() + ";" + ware.getID() +
                   ";" + ware.getPreis() + ";\r\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void listeFuellen() {
    File eingabe = new File("Artikel.txt");

    try {
      Scanner scan = new Scanner(eingabe);

      while (scan.hasNextLine()) {
        String[] text = scan.nextLine().split(";");

        if (!text[0].equals("")) {
          Warentyp temp = new Warentyp(text[0], text[1], text[2],
                                       Double.parseDouble(text[3]));
          warenliste.append(temp);
        }
      }

      scan.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void Warensystem_WindowClosing(WindowEvent evt) {}

  // Ende Methoden
  public static void main(String[] args) {
    new Warensystem("Warensystem");
  }
}
