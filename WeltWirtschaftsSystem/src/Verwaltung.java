import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  *
  * Listenverwaltung
  *
  * @version 1.0 vom 11.01.2018
  * @author Benjamin Reichelt
  */

public class Verwaltung extends JFrame {
  // Anfang Attribute
  private JTextField eingabe = new JTextField();
  private JButton einfuegenKnopf = new JButton();
  private JLabel aktuell = new JLabel();
  private JButton rechtsKnopf = new JButton();
  private JButton linksKnopf = new JButton();
  private JButton anfangKnopf = new JButton();
  private JButton endeKnopf = new JButton();
  private JButton sortButton = new JButton();
  //TODO
  
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JButton delButton = new JButton();
  // Ende Attribute
  
  public Verwaltung(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 310; 
    int frameHeight = 216;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    eingabe.setBounds(8, 16, 177, 33);
    eingabe.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
    cp.add(eingabe);
    einfuegenKnopf.setBounds(200, 16, 75, 33);
    einfuegenKnopf.setText("einfügen");
    einfuegenKnopf.setMargin(new Insets(2, 2, 2, 2));
    einfuegenKnopf.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        einfuegenKnopf_ActionPerformed(evt);
      }
    });
    cp.add(einfuegenKnopf);
    aktuell.setBounds(112, 96, 38, 28);
    aktuell.setText("#");
    aktuell.setBackground(Color.RED);
    aktuell.setOpaque(true);
    aktuell.setHorizontalTextPosition(SwingConstants.CENTER);
    aktuell.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(aktuell);
    rechtsKnopf.setBounds(160, 96, 35, 25);
    rechtsKnopf.setText(">>");
    rechtsKnopf.setMargin(new Insets(2, 2, 2, 2));
    rechtsKnopf.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        rechtsKnopf_ActionPerformed(evt);
      }
    });
    cp.add(rechtsKnopf);
    linksKnopf.setBounds(64, 96, 35, 25);
    linksKnopf.setText("<<");
    linksKnopf.setMargin(new Insets(2, 2, 2, 2));
    linksKnopf.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        linksKnopf_ActionPerformed(evt);
      }
    });
    cp.add(linksKnopf);
    anfangKnopf.setBounds(24, 96, 27, 25);
    anfangKnopf.setText("|<");
    anfangKnopf.setMargin(new Insets(2, 2, 2, 2));
    anfangKnopf.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        anfangKnopf_ActionPerformed(evt);
      }
    });
    cp.add(anfangKnopf);
    endeKnopf.setBounds(208, 96, 35, 25);
    endeKnopf.setText(">|");
    endeKnopf.setMargin(new Insets(2, 2, 2, 2));
    endeKnopf.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        endeKnopf_ActionPerformed(evt);
      }
    });
    cp.add(endeKnopf);
    sortButton.setBounds(48, 136, 75, 25);
    sortButton.setText("sortieren");
    sortButton.setMargin(new Insets(2, 2, 2, 2));
    sortButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        sortButton_ActionPerformed(evt);
      }
    });
    cp.add(sortButton);
    jLabel1.setBounds(72, 56, 54, 20);
    jLabel1.setText("");
    cp.add(jLabel1);
    jLabel2.setBounds(8, 56, 54, 20);
    jLabel2.setText("Anzahl:");
    cp.add(jLabel2);
    delButton.setBounds(128, 136, 75, 25);
    delButton.setText("löschen");
    delButton.setMargin(new Insets(2, 2, 2, 2));
    delButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        delButton_ActionPerformed(evt);
      }
    });
    cp.add(delButton);
    // Ende Komponenten
    setVisible(true);    
  } // end of public Verwaltung
  
  // Anfang Methoden
  public void einfuegenKnopf_ActionPerformed(ActionEvent evt) {
    String temp = eingabe.getText();
    String[] text = temp.split(",");

    for (String s : text) {
      liste.append(new Item (s));
    }
    update();
  } // end of einfuegenKnopf_ActionPerformed

  public void rechtsKnopf_ActionPerformed(ActionEvent evt) {
    liste.next();
    update();
  } // end of rechtsKnopf_ActionPerformed

  public void linksKnopf_ActionPerformed(ActionEvent evt) {
    liste.previous();
    update();
  } // end of linksKnopf_ActionPerformed

  public void anfangKnopf_ActionPerformed(ActionEvent evt) {
    liste.toFirst();
    update();
  } // end of anfangKnopf_ActionPerformed

  public void endeKnopf_ActionPerformed(ActionEvent evt) {
    liste.toLast();
    update();
  } // end of endeKnopf_ActionPerformed

  public void sortButton_ActionPerformed(ActionEvent evt) {
    
    update();
  } // end of sortButton_ActionPerformed

  
  public void update(){
    if (liste.hasAccess()) {
      aktuell.setText(liste.getContent().toString());
      aktuell.setBackground(Color.GREEN);
    }else{
      aktuell.setText("#");
      aktuell.setBackground(Color.RED);
    } // end of if
  }
  public void delButton_ActionPerformed(ActionEvent evt) {
    liste.remove();
    update();
  } // end of delButton_ActionPerformed

  // Ende Methoden
  
  public static void main(String[] args) {
    new Verwaltung("Verwaltung");
  } // end of main
  
} // end of class Verwaltung
