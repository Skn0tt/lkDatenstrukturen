/**
 * Created by simon.knott on 06.12.2017.
 */
public class Main {
    static Wartezimmer wartezimmer = new Wartezimmer();

    public static void main(String... args) {
        // Frau Schmitz (gesetzlich) betritt das Wartezimmer.
        wartezimmer.betreten(new Patient("Frau Schmitz", false));
        wartezimmer.werIstNochDa();
        System.out.println();

        // Frau Müller (privat) betritt das Wartezimmer.
        wartezimmer.betreten(new Patient("Frau Müller", true));
        wartezimmer.werIstNochDa();
        System.out.println();

        // Herr Meyer (privat) betritt das Wartezimmer.
        wartezimmer.betreten(new Patient("Frau Meyer", true));
        wartezimmer.werIstNochDa();
        System.out.println();

        // "Der Nächste bitte."
        wartezimmer.derNaechsteBitte();
        // Herr Schubert (gesetzlich) betritt das Wartezimmer.
        wartezimmer.betreten(new Patient("Herr Schubert", false));
        wartezimmer.werIstNochDa();
        System.out.println();

        // "Der Nächste bitte."
        wartezimmer.derNaechsteBitte();
        // "Der Nächste bitte."
        wartezimmer.derNaechsteBitte();
        // Frau Winter (gesetzlich) betritt das Wartezimmer.
        wartezimmer.betreten(new Patient("Frau Winter", false));
        wartezimmer.werIstNochDa();
    }
}