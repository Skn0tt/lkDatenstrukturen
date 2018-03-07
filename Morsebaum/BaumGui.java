import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

import javazoom.jl.player.Player;


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
  private JButton btnPlay = new JButton();
  private ImageIcon btnPlayIcon = new ImageIcon(this.getClass()
                                                    .getResource("images/play.png"));

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
    btnKlarToMorse.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnKlarToMorse_ActionPerformed(evt);
        }
      });
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
    btnMorseToKlar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnMorseToKlar_ActionPerformed(evt);
        }
      });
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
    btnPlay.setBounds(816, 344, 35, 33);
    btnPlay.setText("");
    btnPlay.setMargin(new Insets(2, 2, 2, 2));
    btnPlay.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnPlay_ActionPerformed(evt);
        }
      });
    btnPlay.setIcon(btnPlayIcon);
    cp.add(btnPlay);
    // Ende Komponenten
    setVisible(true);
    this.canvas.zeichnen(morsebaum.getBaum());
  } // end of public Test

  // Anfang Methoden
  public void btnKlarToMorse_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen  
    // Klartext in Morsetext umwandeln
    
  } // end of btnKlarToMorse_ActionPerformed

  public void btnMorseToKlar_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    // Morsetext in Morsecode umwandeln
    
  } // end of btnMorseToKlar_ActionPerformed

  public void btnPlay_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    // Morsecode abspielen
  } // end of btnPlay_ActionPerformed

  // Ende Methoden
  public static void main(String[] args) {
    new BaumGui("BinaryTree");
  } // end of main
} // end of class Test
