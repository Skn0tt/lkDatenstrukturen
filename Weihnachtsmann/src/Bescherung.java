import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.Stack;

import javax.sound.sampled.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 16.12.2013
  * @author
  */
public class Bescherung extends JFrame {
  private static final String RESOURCES = "./Weihnachtsmann/res/";

  // Anfang Attribute
  private JButton btnKlingeln = new JButton();
  private ImageIcon btnKlingelnIcon = new ImageIcon(RESOURCES + "Glocke.png");
  private JLabel jLabel1 = new JLabel();
  private ImageIcon jLabel1Icon = new ImageIcon(RESOURCES + "Weihnachtsmann.png");
  private JTextField txtWunsch = new JTextField();
  private JButton btnWuenschen = new JButton();
  private JLabel lblGeschenkUnterBaum = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private ImageIcon jLabel3Icon = new ImageIcon(RESOURCES + "Baum.png");
  private JButton lblNaechstesGeschenk = new JButton();
  private JLabel lblAusgabe = new JLabel();

  Stack<String> wuensche = new Stack<>();

  // Ende Attribute
  public Bescherung(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    int frameWidth = 678;
    int frameHeight = 487;
    setSize(frameWidth, frameHeight);
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    btnKlingeln.setBounds(256, 304, 97, 121);
    btnKlingeln.setText("");
    btnKlingeln.setMargin(new Insets(2, 2, 2, 2));
    btnKlingeln.addActionListener(this::btnKlingeln_ActionPerformed);
    btnKlingeln.setIcon(btnKlingelnIcon);
    cp.add(btnKlingeln);
    jLabel1.setBounds(8, 8, 371, 257);
    jLabel1.setText("");
    jLabel1.setIcon(jLabel1Icon);
    cp.add(jLabel1);
    txtWunsch.setBounds(8, 384, 201, 41);
    txtWunsch.setToolTipText("Gib hier deinen Wunsch ein.");
    txtWunsch.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    cp.add(txtWunsch);
    btnWuenschen.setBounds(8, 304, 201, 57);
    btnWuenschen.setText("W�nschen");
    btnWuenschen.setMargin(new Insets(2, 2, 2, 2));
    btnWuenschen.addActionListener(this::btnWuenschen_ActionPerformed);
    btnWuenschen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
    cp.add(btnWuenschen);
    lblGeschenkUnterBaum.setBounds(392, 336, 251, 41);
    lblGeschenkUnterBaum.setText("");
    lblGeschenkUnterBaum.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
    lblGeschenkUnterBaum.setHorizontalTextPosition(SwingConstants.CENTER);
    lblGeschenkUnterBaum.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblGeschenkUnterBaum);
    jLabel3.setBounds(392, 8, 251, 321);
    jLabel3.setText("text");
    jLabel3.setIcon(jLabel3Icon);
    cp.add(jLabel3);
    lblNaechstesGeschenk.setBounds(184, 184, 81, 41);
    lblNaechstesGeschenk.setText("leer");
    lblNaechstesGeschenk.setMargin(new Insets(2, 2, 2, 2));
    lblNaechstesGeschenk.setEnabled(false);
    lblNaechstesGeschenk.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
    cp.add(lblNaechstesGeschenk);
    lblAusgabe.setBounds(192, 32, 315, 41);
    lblAusgabe.setText("");
    lblAusgabe.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
    cp.add(lblAusgabe);
    // Ende Komponenten
    setVisible(true);
  } // end of public Bescherung
  
  // Anfang Methoden
  public void btnKlingeln_ActionPerformed(ActionEvent evt) {
    if (!wuensche.empty()) lblGeschenkUnterBaum.setText(wuensche.pop());

    jLabel1.setText(wuensche.empty() ? "leer" : wuensche.peek());
    
    //Sound abspielen
    new Sound(RESOURCES + "XMasBell.wav").start();
  } // end of btnKlingeln_ActionPerformed
  
  public void btnWuenschen_ActionPerformed(ActionEvent evt) {
    wuensche.push(txtWunsch.getText());

    jLabel1.setText(wuensche.peek());
  } // end of btnWuenschen_ActionPerformed
  
  // Ende Methoden
  public static void main(String[] args) {
    new Bescherung("Bescherung");
  } // end of main
} // end of class ChristkindStack
