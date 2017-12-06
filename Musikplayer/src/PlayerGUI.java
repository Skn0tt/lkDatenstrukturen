import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 08.11.2016
  * @author Benjamin Reichelt
  */
public class PlayerGUI extends JFrame {
  // Anfang Attribute
  private JSeparator jSeparator1 = new JSeparator();
  private JButton hinzuBT = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JLabel liedLB = new JLabel();
  private JButton abspielenBT = new JButton();
  private ImageIcon abspielenBTIcon = new ImageIcon(ClassLoader.getSystemResource("play.png"));
  private Playlist playlist;
  private JTextArea wiedergabe = new JTextArea("");
  private JScrollPane wiedergabeScrollPane = new JScrollPane(wiedergabe);
  private JFileChooser jFileChooser1 = new JFileChooser();
  private JButton stopBTN = new JButton();
  private ImageIcon stopBTNIcon = new ImageIcon(ClassLoader.getSystemResource("stopp.png"));

  // Ende Attribute
  public PlayerGUI() {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 596;
    int frameHeight = 338;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("MP3-Player");
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    playlist = new Playlist();

    jSeparator1.setBounds(24, 64, 313, 209);
    cp.add(jSeparator1);
    hinzuBT.setBounds(24, 16, 137, 25);
    hinzuBT.setText("Hinzufügen");
    hinzuBT.setMargin(new Insets(2, 2, 2, 2));
    hinzuBT.addActionListener(this::hinzuBT_ActionPerformed);
    hinzuBT.setBackground(new Color(0x404040));
    hinzuBT.setForeground(Color.GREEN);
    cp.add(hinzuBT);
    jLabel1.setBounds(360, 8, 195, 25);
    jLabel1.setText("Aktuell gespielter Titel:");
    jLabel1.setForeground(Color.GREEN);
    jLabel1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
    cp.add(jLabel1);
    liedLB.setBounds(360, 48, 203, 33);
    liedLB.setText("");
    liedLB.setBackground(new Color(0x404040));
    liedLB.setOpaque(true);
    liedLB.setForeground(Color.GREEN);
    cp.add(liedLB);
    abspielenBT.setBounds(176, 0, 57, 65);
    abspielenBT.setText("");
    abspielenBT.setMargin(new Insets(2, 2, 2, 2));
    abspielenBT.addActionListener(this::abspielenBT_ActionPerformed);
    abspielenBT.setForeground(Color.GREEN);
    abspielenBT.setIcon(abspielenBTIcon);
    abspielenBT.setBorderPainted(false);
    abspielenBT.setFocusPainted(true);
    cp.add(abspielenBT);
    wiedergabeScrollPane.setBounds(24, 80, 305, 185);
    wiedergabe.setBackground(new Color(0x404040));
    wiedergabe.setForeground(Color.GREEN);
    cp.add(wiedergabeScrollPane);
    cp.setBackground(Color.BLACK);
    stopBTN.setBounds(248, 0, 59, 65);
    stopBTN.setText("");
    stopBTN.setMargin(new Insets(2, 2, 2, 2));
    stopBTN.addActionListener(this::stopBTN_ActionPerformed);
    stopBTN.setBorderPainted(false);
    stopBTN.setIcon(stopBTNIcon);
    stopBTN.setBackground(Color.BLACK);
    cp.add(stopBTN);
    jFileChooser1.setAcceptAllFileFilterUsed(true);
    // Ende Komponenten
    stopBTN.setVisible(false);
    setVisible(true);
  } // end of public PlayerGUI

  // Anfang Methoden
  public static void main(String[] args) {
    new PlayerGUI();
  } // end of main

  public void hinzuBT_ActionPerformed(ActionEvent evt) {
    String pfad = jFileChooser1_openFile().getAbsolutePath();

    playlist.hinzufuegen(pfad);

    String anzeige = playlist.anzeigen();
    wiedergabe.setText(anzeige);
  } // end of hinzuBT_ActionPerformed

  public void abspielenBT_ActionPerformed(ActionEvent evt) {
    playlist.abspielen();

    String aktuell = playlist.aktuellerTitel();
    liedLB.setText(aktuell);

    String anzeige = playlist.anzeigen();
    wiedergabe.setText(anzeige);
    abspielenBT.setVisible(false);
    stopBTN.setVisible(true);
  } // end of abspielenBT_ActionPerformed

  public File jFileChooser1_openFile() {
    File workingDirectory = new File(System.getProperty("user.dir") + "/mp3s");
    jFileChooser1.setCurrentDirectory(workingDirectory);

    if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      return jFileChooser1.getSelectedFile();
    } else {
      return null;
    }
  }

  public void stopBTN_ActionPerformed(ActionEvent evt) {
    playlist.stop();
    abspielenBT.setVisible(true);
    stopBTN.setVisible(false);
  } // end of stopBTN_ActionPerformed

  // Ende Methoden
} // end of class PlayerGUI
