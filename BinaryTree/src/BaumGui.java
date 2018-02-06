/*
 * Copyright (c) Simon Knott 2018.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 18.04.2012
  * @author
  */
public class BaumGui extends JFrame {
  // Anfang Attribute
  private Canvas<Integer> canvas1 = new Canvas<>();
  private BinaryTree<Integer> tree;
  private JLabel lblTitle = new JLabel();
  private JButton btnZeichnen = new JButton();
  private JButton btnAnzahl = new JButton();
  private JButton btnHoehe = new JButton();
  private JLabel lblHoehe = new JLabel();
  private JLabel lblAnzahl = new JLabel();
  private JButton btnPreorder = new JButton();
  private JButton btnInorder = new JButton();
  private JButton btnPostorder = new JButton();
  private JLabel lblPreorder = new JLabel();
  private JLabel lblInorder = new JLabel();
  private JLabel lblPostorder = new JLabel();
  private JButton btnKnotenAnzahl = new JButton();
  private JLabel lblKnotenAnzahl = new JLabel();
  private JButton btnTiefe = new JButton();
  private JLabel lblTiefe = new JLabel();
  private JTextField txtPreorderInput = new JTextField();
  private JLabel labelPreorderInput = new JLabel();
  private JLabel jLabel1 = new JLabel();
  private JTextField txtInorderInput = new JTextField();
  private JLabel labelPreorderInput1 = new JLabel();
  private JButton btnBaumErstellen = new JButton();

  // Ende Attribute
  public BaumGui(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 1099;
    int frameHeight = 789;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    canvas1.setBounds(8, 64, 1065, 417);
    cp.add(canvas1);
    lblTitle.setBounds(8, 8, 1067, 57);
    lblTitle.setText("Visualisierung BinaryTree");
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("Calibri", Font.BOLD, 36));
    cp.add(lblTitle);
    btnZeichnen.setBounds(8, 488, 161, 49);
    btnZeichnen.setText("Zeichnen");
    btnZeichnen.setMargin(new Insets(2, 2, 2, 2));
    btnZeichnen.addActionListener(this::btnZeichnen_ActionPerformed);
    btnZeichnen.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnZeichnen);

    btnPreorder.setBounds(8, 544, 161, 49);
    btnPreorder.setText("Preorder");
    btnPreorder.setMargin(new Insets(2, 2, 2, 2));
    btnPreorder.addActionListener(this::btnPreorder_ActionPerformed);
    btnPreorder.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnPreorder);
    btnInorder.setBounds(8, 600, 161, 49);
    btnInorder.setText("Inorder");
    btnInorder.setMargin(new Insets(2, 2, 2, 2));
    btnInorder.addActionListener(this::btnInorder_ActionPerformed);
    btnInorder.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnInorder);
    btnPostorder.setBounds(8, 656, 161, 49);
    btnPostorder.setText("Postorder");
    btnPostorder.setMargin(new Insets(2, 2, 2, 2));
    btnPostorder.addActionListener(this::btnPostorder_ActionPerformed);
    btnPostorder.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnPostorder);
    lblPreorder.setBounds(176, 552, 435, 33);
    lblPreorder.setText("");
    lblPreorder.setFont(new Font("Dialog", Font.BOLD, 22));
    cp.add(lblPreorder);
    lblInorder.setBounds(176, 608, 435, 33);
    lblInorder.setText("");
    lblInorder.setEnabled(true);
    lblInorder.setFont(new Font("Dialog", Font.BOLD, 22));
    cp.add(lblInorder);
    lblPostorder.setBounds(176, 664, 435, 33);
    lblPostorder.setText("");
    lblPostorder.setEnabled(true);
    lblPostorder.setFont(new Font("Dialog", Font.BOLD, 22));
    cp.add(lblPostorder);
    btnKnotenAnzahl.setBounds(176, 488, 145, 49);
    btnKnotenAnzahl.setText("Z�hle Knoten");
    btnKnotenAnzahl.setMargin(new Insets(2, 2, 2, 2));
    btnKnotenAnzahl.addActionListener(this::btnKnotenAnzahl_ActionPerformed);
    btnKnotenAnzahl.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnKnotenAnzahl);
    lblKnotenAnzahl.setBounds(328, 496, 107, 33);
    lblKnotenAnzahl.setText("");
    lblKnotenAnzahl.setFont(new Font("Dialog", Font.BOLD, 22));
    lblKnotenAnzahl.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblKnotenAnzahl);
    btnTiefe.setBounds(448, 488, 161, 49);
    btnTiefe.setText("Bestimme Tiefe");
    btnTiefe.setMargin(new Insets(2, 2, 2, 2));
    btnTiefe.addActionListener(this::btnTiefe_ActionPerformed);
    btnTiefe.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnTiefe);
    lblTiefe.setBounds(614, 496, 107, 33);
    lblTiefe.setText("");
    lblTiefe.setFont(new Font("Dialog", Font.BOLD, 22));
    lblTiefe.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lblTiefe);
    txtPreorderInput.setBounds(832, 552, 241, 33);
    cp.add(txtPreorderInput);
    labelPreorderInput.setBounds(688, 560, 139, 20);
    labelPreorderInput.setText("Preorder-Traversierung");
    cp.add(labelPreorderInput);
    jLabel1.setBounds(736, 520, 334, 30);
    jLabel1.setText("Baumeingabe");
    jLabel1.setFont(new Font("Dialog", Font.BOLD, 22));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel1);
    txtInorderInput.setBounds(832, 592, 241, 33);
    cp.add(txtInorderInput);
    labelPreorderInput1.setBounds(694, 597, 131, 20);
    labelPreorderInput1.setText("Inorder-Traversierung");
    cp.add(labelPreorderInput1);
    btnBaumErstellen.setBounds(688, 632, 385, 49);
    btnBaumErstellen.setText("Baum aus Traversierung ersellen");
    btnBaumErstellen.setMargin(new Insets(2, 2, 2, 2));
    btnBaumErstellen.addActionListener(this::btnBaumErstellen_ActionPerformed);
    btnBaumErstellen.setFont(new Font("Calibri", Font.BOLD, 22));
    cp.add(btnBaumErstellen);
    // Ende Komponenten
    setVisible(true);

    //Baum tree anlegen
    BinaryTree<Integer> b1 = new BinaryTree<>(2, new BinaryTree<>(43), null);
    BinaryTree<Integer> b2 = new BinaryTree<>(5);
    BinaryTree<Integer> b3 = new BinaryTree<>(7, b1, b2);
    BinaryTree<Integer> b4 = new BinaryTree<>(8, null, new BinaryTree<>(20));
    BinaryTree<Integer> b5 = new BinaryTree<>(4);
    BinaryTree<Integer> b6 = new BinaryTree<>(11, b4, b5);
    tree = new BinaryTree<>(14, b3, b6);
  } // end of public Test

  // Anfang Methoden
  public void btnZeichnen_ActionPerformed(ActionEvent evt) {
    canvas1.zeichnen(tree);
  } // end of btnZeichnen_ActionPerformed

  public void btnPreorder_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    String pre = TreeOutput.preOrder(tree);
    lblPreorder.setText(pre);
  } // end of btnPreorder_ActionPerformed

  public void btnInorder_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    String in = TreeOutput.inOrder(tree);
    lblInorder.setText(in);
  } // end of btnInorder_ActionPerformed

  public void btnPostorder_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    String post = TreeOutput.postOrder(tree);
    lblPostorder.setText(post);
  } // end of btnPostorder_ActionPerformed

  public void btnKnotenAnzahl_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen
    lblKnotenAnzahl.setText(TreeOutput.getNodeCount(tree) + "");
  } // end of btnKnotenAnzahl_ActionPerformed

  public void btnTiefe_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einf�gen  
    lblTiefe.setText(TreeOutput.getDepth(tree) + "");
  } // end of btnTiefe_ActionPerformed

  public void btnBaumErstellen_ActionPerformed(ActionEvent evt) {
    String preOrder = txtPreorderInput.getText();
    String inOrder = txtInorderInput.getText();

    List<Integer> preOrderList = new List<>();
    List<Integer> inOrderList = new List<>();

    for (String s : preOrder.split(",")) { preOrderList.append(Integer.parseInt(s)); }
    for (String s : inOrder.split(",")) { inOrderList.append(Integer.parseInt(s)); }

    this.tree = Reconstruct.preOrderInOrder(preOrderList, inOrderList);
    canvas1.zeichnen(tree);
  } // end of btnBaumErstellen_ActionPerformed

  // Ende Methoden
  public static void main(String[] args) {
    new BaumGui("BinaryTree");
  } // end of main
} // end of class Test
