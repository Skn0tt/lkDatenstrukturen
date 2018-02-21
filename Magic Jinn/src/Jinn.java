import java.util.List;

/**
 * Beschreibung
 *
 * @author B. Reichelt
 * @version 1.0 vom 01.12.2015
 */
public class Jinn {
    private BinaryTree<String> ratebaum;
    private BinaryTree<String> aktuell;

    // Anfang Attribute
    // Ende Attribute
    public Jinn() {
        ratebaum = new BinaryTree("Ist das Tier ein S‰ugetier?",
                new BinaryTree("Lebt dein Tier im Wasser?",
                        new BinaryTree("Wal"),
                        new BinaryTree("Huhn")),
                new BinaryTree("Ist dein Tier ein Insekt?",
                        new BinaryTree("Spinne"),
                        new BinaryTree("Kann dein Tier fliegen?",
                                new BinaryTree("Ist dein Tier groﬂ?",
                                        new BinaryTree("Storch"),
                                        new BinaryTree("Amsel")),
                                new BinaryTree("Katze"))));
    }

    public String ersteFrage() {
        aktuell = ratebaum;
        if (aktuell.getLeftTree().isEmpty()) {
            return "Ist dein Tier ein(e) " + aktuell.getContent() + "?";
        } else {
            return aktuell.getContent();
        }
    }

    public String aktuelleFrage() {
        if (aktuell.isEmpty()) {
            return "Hm. Ich weiﬂ nicht, was du meinst.\nBitte erweitere den Baum.";
        }

        if (aktuell.getLeftTree().isEmpty()) {
            return "Ist es ein " + aktuell.getContent() + "?";
        }

        return aktuell.getContent();
    }

    public String frageMitJaBeantwortet() {
        if (aktuell.getLeftTree().isEmpty()) {
            return "Ich bin gut!";
        }

        aktuell = aktuell.getLeftTree();

        return aktuelleFrage();
    }

    public String frageMitNeinBeantwortet() {
        if (aktuell.getRightTree().isEmpty()) {
            return null;
        }

        aktuell = aktuell.getRightTree();

        return aktuelleFrage();
    }

    public String getAktuell() {
        return aktuell.getContent();
    }

    public BinaryTree<String> getRatebaum() {
        return ratebaum;
    }

    public void erweitern(String pFrage, String pTierLinks) {
        aktuell.setLeftTree(new BinaryTree<>(pTierLinks));
        aktuell.setRightTree(new BinaryTree<>(aktuell.getContent()));
        aktuell.setContent(pFrage);
    }

    private boolean isQuestion(String s) {
        return s.contains("?");
    }

    public void load(List<String> s) {

    }

    private BinaryTree<String> traverse(List<String> s) {
        if (!isQuestion(s.get(0))) {
            return new BinaryTree<>(s.get(0));
        }

        for (String v : s) {
            return new BinaryTree<>();
        }
    }

    // Anfang Methoden
    // Ende Methoden
} // end of Jinn
