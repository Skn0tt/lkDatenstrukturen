import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class BaumGui<ContentType> extends JFrame {
  // Anfang Attribute
  private Canvas canvas1 = new Canvas();
  // Ende Attribute

  public BaumGui(String title, BinaryTree<ContentType> baum) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1077; 
    int frameHeight = 428;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    canvas1.setBounds(16, 8, 1041, 377);
    cp.add(canvas1);
    // Ende Komponenten

    setVisible(true);
    canvas1.zeichnen(baum);
  } // end of public Test
} // end of class Test
