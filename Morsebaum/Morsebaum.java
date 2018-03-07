/**
  *
  * Die Klasse enthaelt Informationen des Morsealphabets in Form eines
  * binaeren Baums. Mithilfe der Klasse können Morsezeichen und Klartextzeichen
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
    lBaum4Rechts = new BinaryTree<>(new Character('Ü'));
    BinaryTree<Character> lBaum3Rechts = new BinaryTree<>(new Character('U'), lBaum4Links, lBaum4Rechts);
    BinaryTree<Character> lBaum2Links = new BinaryTree<>(new Character('I'), lBaum3Links, lBaum3Rechts);
    lBaum4Links = new BinaryTree<>(new Character('L'));
    lBaum4Rechts = new BinaryTree<>(new Character('Ä'));
    lBaum3Links = new BinaryTree<>(new Character('R'), lBaum4Links, lBaum4Rechts);
    lBaum4Links = new BinaryTree<>(new Character('P'));
    lBaum4Rechts = new BinaryTree<>(new Character('J'));
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
    lBaum4Links = new BinaryTree<>(new Character('Ö'));
    lBaum4Rechts = new BinaryTree<>(new Character('@'));
    lBaum3Rechts = new BinaryTree<>(new Character('O'),lBaum4Links, lBaum4Rechts);
    lBaum2Rechts = new BinaryTree<>(new Character('M'), lBaum3Links, lBaum3Rechts);
    BinaryTree<Character> lBaum1Rechts = new BinaryTree<>(new Character('T'), lBaum2Links, lBaum2Rechts);
    baum = new BinaryTree<>(new Character('#'), lBaum1Links, lBaum1Rechts);
    
  }

  // Anfang Methoden

  
  public char decodiereBuchstabe(String pCode){
    // TODO hier Quelltext einfügen
    return 'a';
  }
  

  
  public String codiereBuchstabe(char pChar){
    // TODO hier Quelltext einfügen
    return null;
  }

  
  private String codiereBuchstabe(char pChar, BinaryTree<Character> baum){
    // TODO hier Quelltext einfügen
    return null;
  }

  public BinaryTree<Character> getBaum() {
    return baum;
  }

  // Ende Methoden
}
