/*
 * Copyright (c) Simon Knott 2018.
 */

/**
  *
  * Die Klasse enthaelt Informationen des Morsealphabets in Form eines
  * binaeren Baums. Mithilfe der Klasse k�nnen Morsezeichen und Klartextzeichen
  * jeweils ineinander ueberfuehrt werden.
  *
  * @version 2018-03-18
  * @author Benjamin Reichelt
  */
public class Morsebaum {
  // Anfang Attribute
  private BinaryTree<Character> baum;

  // Ende Attribute
  public Morsebaum() {
    BinaryTree<Character> lBaum4Links = new BinaryTree<>(new Character('H'));
    BinaryTree<Character> lBaum4Rechts = new BinaryTree<>(new Character('V'));
    BinaryTree<Character> lBaum3Links = new BinaryTree<>(new Character('S'),lBaum4Links, lBaum4Rechts);
    lBaum4Links = new BinaryTree<>(new Character('F'));
    lBaum4Rechts = new BinaryTree<>(new Character('�'));
    BinaryTree<Character> lBaum3Rechts = new BinaryTree<>(new Character('U'), lBaum4Links, lBaum4Rechts);
    BinaryTree<Character> lBaum2Links = new BinaryTree<>(new Character('I'), lBaum3Links, lBaum3Rechts);
    lBaum4Links = new BinaryTree<>(new Character('L'));
    lBaum4Rechts = new BinaryTree<>(new Character('�'));
    lBaum3Links = new BinaryTree<>('R', lBaum4Links, lBaum4Rechts);
    lBaum4Links = new BinaryTree<>(new Character('P'));
    lBaum4Rechts = new BinaryTree<>('J');
    lBaum3Rechts = new BinaryTree<>(new Character('W'), lBaum4Links, lBaum4Rechts);
    BinaryTree<Character> lBaum2Rechts = new BinaryTree<>(new Character('A'), lBaum3Links, lBaum3Rechts);
    BinaryTree<Character> lBaum1Links = new BinaryTree<>(new Character('E'), lBaum2Links, lBaum2Rechts);
    lBaum4Links = new BinaryTree<>(new Character('B'));
    lBaum4Rechts = new BinaryTree<>(new Character('X'));
    lBaum3Links = new BinaryTree<>(new Character('D'), lBaum4Links, lBaum4Rechts);
    lBaum4Links = new BinaryTree<>(new Character('C'));
    lBaum4Rechts = new BinaryTree<>(new Character('Y'));
    lBaum3Rechts = new BinaryTree<>(new Character('K'), lBaum4Links, lBaum4Rechts);
    lBaum2Links = new BinaryTree<>(new Character('N'), lBaum3Links, lBaum3Rechts);
    lBaum4Links = new BinaryTree<>(new Character('Q'));
    lBaum4Rechts = new BinaryTree<>(new Character('Z'));
    lBaum3Links = new BinaryTree<>(new Character('G'), lBaum4Links, lBaum4Rechts);
    lBaum4Links = new BinaryTree<>(new Character('�'));
    lBaum4Rechts = new BinaryTree<>(new Character('@'));
    lBaum3Rechts = new BinaryTree<>(new Character('O'),lBaum4Links, lBaum4Rechts);
    lBaum2Rechts = new BinaryTree<>(new Character('M'), lBaum3Links, lBaum3Rechts);
    BinaryTree<Character> lBaum1Rechts = new BinaryTree<>(new Character('T'), lBaum2Links, lBaum2Rechts);
    baum = new BinaryTree<>(new Character('#'), lBaum1Links, lBaum1Rechts);
    
  }

  // Anfang Methoden

  
  public String decodiereBuchstabe(String pCode){
    BinaryTree<Character> tree = getBaum();

    if (pCode.equals("##")) {
      return " ";
    }

    if (pCode.equals("#")) {
      return "";
    }

    for (char c : pCode.trim().toCharArray()) {
      switch (c) {
        case '.':
          tree = tree.getLeftTree();
          break;
        case '-':
          tree = tree.getRightTree();
          break;
        default:
          throw new IllegalArgumentException("Don't know character...");
      }
    }

    return tree.getContent().toString();
  }
  
  public String codiereBuchstabe(char pChar){
    return codiereBuchstabe(pChar, getBaum(), "");
  }

  public String codiere(String klar) {
    StringBuilder result = new StringBuilder();

    char[] chars = klar.toUpperCase().toCharArray();

    for (char c : chars) {
      if (c == ' ') {
        result.append(" ## ");
      } else {
        result.append(codiereBuchstabe(c));
        result.append(" # ");
      }
    }

    return result.toString();
  }

  public String decodiere(String morse) {
    StringBuilder result = new StringBuilder();

    String[] chars = morse.split(" ");

    for (String c : chars) {
      result.append(decodiereBuchstabe(c));
    }

    return result.toString();
  }
  
  private String codiereBuchstabe(char pChar, BinaryTree<Character> baum, String code){
    if (baum.isEmpty()) {
      return null;
    }
    if (baum.getContent() == pChar) {
      return code;
    }

    String resultLeft = codiereBuchstabe(pChar, baum.getLeftTree(), code);
    if (resultLeft != null) {
      return resultLeft + ".";
    }

    String resultRight = codiereBuchstabe(pChar, baum.getRightTree(), code);
    if (resultRight != null) {
      return resultRight + "-";
    }

    return null;
  }

  public BinaryTree<Character> getBaum() {
    return baum;
  }

  // Ende Methoden
}
