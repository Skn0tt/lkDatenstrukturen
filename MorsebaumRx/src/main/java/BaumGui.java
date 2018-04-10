/*
 * Copyright (c) Simon Knott 2018.
 */

import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 06.03.2018
  * @author
  */
public class BaumGui extends JFrame {
  // Anfang Attribute
  private Canvas<Character> canvas = new Canvas<Character>();
  
  private JLabel lblMorse = new JLabel();
  private Player kurz;
  private Player lang;
  private JButton btnAnzahl = new JButton();
  private JButton btnHoehe = new JButton();
  private JLabel lblHoehe = new JLabel();
  private JLabel lblAnzahl = new JLabel();
  private JButton btnKlarToMorse = new JButton();
  private JLabel lKlartext = new JLabel();
  private JLabel lblMorsecode = new JLabel();
  private JButton btnMorseToKlar = new JButton();
  private JTextArea jtAKlartext = new JTextArea("");
  private JScrollPane jtAKlartextScrollPane = new JScrollPane(jtAKlartext);
  private JTextArea jtAMorsecode = new JTextArea("");
  private JScrollPane jtAMorsecodeScrollPane = new JScrollPane(jtAMorsecode);
  private JButton btnRecord = new JButton();
  private ImageIcon btnStopIcon = new ImageIcon(this.getClass().getResource("images/stop.png"));
  private ImageIcon btnRecordIcon = new ImageIcon(this.getClass().getResource("images/record.png"));

  private boolean isRecording = false;

  private Morsebaum morsebaum = new Morsebaum();

  Transcriptor t;
  // Ende Attribute
  public BaumGui(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 870;
    int frameHeight = 530;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    canvas.setBounds(8, 64, 841, 273);
    cp.add(canvas);
    lblMorse.setText("Morsebaum");
    lblMorse.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblMorse);

    btnKlarToMorse.setBounds(352, 376, 161, 49);
    btnKlarToMorse.setText(">>");
    btnKlarToMorse.setMargin(new Insets(2, 2, 2, 2));
    btnKlarToMorse.addActionListener(this::btnKlarToMorse_ActionPerformed);
    btnKlarToMorse.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnKlarToMorse);
    lKlartext.setBounds(8, 344, 334, 30);
    lKlartext.setText("Klartext");
    lKlartext.setFont(new Font("Dialog", Font.BOLD, 22));
    lKlartext.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lKlartext);
    lblMorse.setBounds(8, 8, 851, 57);
    lblMorse.setText("Morsecode");
    lblMorse.setFont(new Font("Calibri", Font.BOLD, 36));
    lblMorse.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblMorse);
    btnMorseToKlar.setBounds(352, 432, 161, 49);
    btnMorseToKlar.setText("<<");
    btnMorseToKlar.setMargin(new Insets(2, 2, 2, 2));
    btnMorseToKlar.addActionListener(this::btnMorseToKlar_ActionPerformed);
    btnMorseToKlar.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnMorseToKlar);
    jtAKlartextScrollPane.setBounds(8, 376, 336, 108);
    jtAKlartext.setFont(new Font("Dialog", Font.BOLD, 18));
    cp.add(jtAKlartextScrollPane);
    jtAMorsecodeScrollPane.setBounds(520, 376, 328, 108);
    jtAMorsecode.setFont(new Font("Dialog", Font.BOLD, 18));
    cp.add(jtAMorsecodeScrollPane);
    canvas.setBounds(8, 64, 849, 273);
    lblMorsecode.setBounds(520, 344, 334, 30);
    btnRecord.setBounds(816, 344, 35, 33);
    btnRecord.setText("");
    btnRecord.setMargin(new Insets(2, 2, 2, 2));
    btnRecord.addActionListener(this::btnPlay_ActionPerformed);
    btnRecord.setIcon(btnRecordIcon);
    cp.add(btnRecord);
    // Ende Komponenten
    setVisible(true);
    this.canvas.zeichnen(morsebaum.getBaum());
  } // end of public Test

  /**
   * # Handlers
   */

  // Anfang Methoden
  public void btnKlarToMorse_ActionPerformed(ActionEvent evt) {
    this.jtAMorsecode.setText(morsebaum.codiere(this.jtAKlartext.getText()));
    // Klartext in Morsetext umwandeln
    
  } // end of btnKlarToMorse_ActionPerformed

  public void btnMorseToKlar_ActionPerformed(ActionEvent evt) {
    this.jtAKlartext.setText(morsebaum.decodiere(this.jtAMorsecode.getText()));
    // Morsetext in Morsecode umwandeln
    
  } // end of btnMorseToKlar_ActionPerformed

  public void btnPlay_ActionPerformed(ActionEvent evt) {
    if (isRecording) {
      this.isRecording = false;
      stopRecording();
      btnRecord.setIcon(btnRecordIcon);
    } else {
      this.isRecording = true;
      startRecording();
      btnRecord.setIcon(btnStopIcon);
    }
  } // end of btnPlay_ActionPerformed

  /**
   * # Logic
   */
  void startRecording() {
    // Start Transcriptor
    if (t != null) {
      t.stop();
    }

    t = new Transcriptor();

    t.registerListener(code -> this.jtAMorsecode.setText(this.jtAMorsecode.getText() + code));

    t.start();
  }
  void stopRecording() {
    t.unregisterListeners();
    t.stop();
  }

  // Ende Methoden
  public static void main(String[] args) {
    new BaumGui("BinaryTree");
  } // end of main
} // end of class Test
