import java.awt.*;

import javax.swing.*;


public class Canvas<ContentType> extends JPanel {
  private BinaryTree<ContentType> b;
  
  public void zeichnen(Graphics g, BinaryTree<ContentType> b, int l, int r,
  int t) {
    g.setFont(new Font("Serif", Font.PLAIN, 20));
    
    if ((b != null) && !b.isEmpty()) {
      String s = ("" + b.getContent().toString());
      int x = ((l + r) - (s.length() * 7)) / 2;
      g.setColor(Color.blue);
      g.drawString(s, x, t * 36);
      
      int x1 = (l + r) / 2;
      int y1 = (t * 36) + 7;
      int x2 = (l + x1) / 2;
      int x3 = (x1 + r) / 2;
      int y2 = y1 + 36;
      g.setColor(Color.red);
      g.fillOval(x1 - 3, y1 - 3, 10, 10);
      if ((b.getLeftTree() != null) && !b.getLeftTree().isEmpty()) {
        g.drawLine(x1, y1, x2, y2);
      } else {
        g.fillOval(x1 - 3, y1 - 3, 10, 10);
      }
      
      if ((b.getRightTree() != null) && !b.getRightTree().isEmpty()) {
        g.drawLine(x1, y1, x3, y2);
      } else {
        g.fillOval(x1 - 3, y1 - 3, 10, 10);
      }
      
      zeichnen(g, b.getLeftTree(), l, (l + r) / 2, t + 1);
      zeichnen(g, b.getRightTree(), (l + r) / 2, r, t + 1);
    }
  }
  
  public void zeichnen(BinaryTree<ContentType> b) {
    this.b = b;
    repaint();
  }
  
  public void paint(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(0, 0, getSize().width, getSize().height);
    g.setColor(Color.blue);
    zeichnen(g, b, 1, this.getWidth(), 1);
  }
}
