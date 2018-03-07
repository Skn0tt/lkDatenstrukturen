/**
 * Klasse BinaryTree
 * Mithilfe der generischen Klasse BinaryTree koennen beliebig viele Objekte vom
 * Typ ContentType in einem Binaerbaum verwaltet werden. Ein Objekt der Klasse
 * stellt entweder einen leeren Baum dar oder verwaltet ein Inhaltsobjekt
 * sowie einen linken und einen rechten Teilbaum, die ebenfalls Objekte der
 * generischen Klasse BinaryTree sind.
 *
 * @version 2015-06-24
 * @author  Benjamin Reichelt
 */
public class BinaryTree<ContentType> {
  private ContentType content;
  private BinaryTree<ContentType> leftTree;
  private BinaryTree<ContentType> rightTree;

  /**
   * Nach dem Aufruf des Konstruktors existiert ein leerer Binaerbaum.
   */
  public BinaryTree() {
    this.content = null;
    this.leftTree = null;
    this.rightTree = null;
  }

  /**
   * Wenn der Parameter pContent ungleich null ist, existiert nach dem Aufruf
   * des Konstruktors der Binaerbaum und hat pContent als Inhaltsobjekt und
   * zwei leere Teilbaeume. Falls der Parameter null ist, wird ein leerer
   * Binaerbaum erzeugt.
   *
   * @param pContent  Inhaltsobjekt
   */
  public BinaryTree(ContentType pContent) {
    if (pContent == null) {
      this.content = null;
      this.leftTree = null;
      this.rightTree = null;
    } else {
      this.content = pContent;
      this.leftTree = new BinaryTree<ContentType>();
      this.rightTree = new BinaryTree<ContentType>();
    }
  }

  /**
   * Wenn der Parameter pContent ungleich null ist, wird ein Binaerbaum mit
   * pContent als Inhaltsobjekt und den beiden Teilbaeume pLeftTree und
   * pRightTree erzeugt. Sind pLeftTree oder pRightTree gleich null, wird
   * der entsprechende Teilbaum als leerer Binaerbaum eingefuegt. Wenn der
   * Parameter pContent gleich null ist, wird ein leerer Binaerbaum erzeugt.
   *
   * @param pContent Inhaltsobjekt
   * @param pLeftTree linker Binaerbaum
   * @param pRightTree rechter Binaerbaum
   */
  public BinaryTree(ContentType pContent, BinaryTree<ContentType> pLeftTree,
                    BinaryTree<ContentType> pRightTree) {
    if (pContent == null) {
      this.content = null;
      this.leftTree = null;
      this.rightTree = null;
    } else {
      this.content = pContent;

      if (pLeftTree != null) {
        this.leftTree = pLeftTree;
      } else {
        this.leftTree = new BinaryTree<ContentType>();
      }

      if (pRightTree != null) {
        this.rightTree = pRightTree;
      } else {
        this.rightTree = new BinaryTree<ContentType>();
      }
    }
  }

  /**
   * Diese Anfrage liefert den Wahrheitswert true, wenn der Binaerbaum leer ist,
   * sonst liefert sie den Wert false.
   *
   * @return true, wenn der Binaerbaum leer ist, sonst false
   */
  public boolean isEmpty() {
    return (this.content == null);
  }

  /**
   * Wenn der Binaerbaum leer ist, wird der Parameter pContent als Inhaltsobjekt
   * sowie ein leerer linker und rechter Teilbaum eingefuegt. Ist der Binaerbaum
   * nicht leer, wird das Inhaltsobjekt durch pContent ersetzt. Die Teilbaeume
   * werden nicht geaendert. Wenn pContent gleich null ist, bleibt der
   * Binaerbaum unveraendert.
   *
   * @param pContent neues Inhaltsobjekt
   */
  public void setContent(ContentType pContent) {
    if (pContent != null) {
      if (this.isEmpty()) {
        this.leftTree = new BinaryTree<ContentType>();
        this.rightTree = new BinaryTree<ContentType>();
      }

      this.content = pContent;
    }
  }

  /**
   * Diese Anfrage liefert das Inhaltsobjekt des Binaerbaums. Wenn der
   * Binaerbaum leer ist, wird null zurueckgegeben.
   *
   * @return Inhaltsobjekt (bzw. null, wenn der Baum leer ist)
   */
  public ContentType getContent() {
    return content;
  }

  /**
   * Wenn der Binaerbaum leer ist, wird pTree nicht angehaengt. Andernfalls
   * erhaelt der Binaerbaum den uebergebenen Baum als linken Teilbaum.
   * Falls der Parameter null ist, aendert sich nichts.
   *
   * @param pTree neuer linker Teilbaum
   */
  public void setLeftTree(BinaryTree<ContentType> pTree) {
    if (!this.isEmpty() && (pTree != null)) {
      leftTree = pTree;
    }
  }

  /**
   * Wenn der Binaerbaum leer ist, wird pTree nicht angehaengt. Andernfalls
   * erhaelt der Binaerbaum den uebergebenen Baum als rechten Teilbaum.
   * Falls der Parameter null ist, aendert sich nichts.
   *
   * @param pTree neuer rechter Teilbaum
   */
  public void setRightTree(BinaryTree<ContentType> pTree) {
    if (!this.isEmpty() && (pTree != null)) {
      rightTree = pTree;
    }
  }

  /**
   * Diese Anfrage liefert den linken Teilbaum des Binaerbaumes. Der Binaerbaum
   * aendert sich nicht. Wenn der Binaerbaum leer ist, wird null zurueckgegeben.
   *
   * @return linker Teilbaum
   */
  public BinaryTree<ContentType> getLeftTree() {
    return leftTree;
  }

  /**
   * Diese Anfrage liefert den rechten Teilbaum des Binaerbaumes. Der Binaerbaum
   * aendert sich nicht. Wenn der Binaerbaum leer ist, wird null zurueckgegeben.
   *
   * @return rechter Teilbaum
   */
  public BinaryTree<ContentType> getRightTree() {
    return rightTree;
  }
}
