/**
 * Created by simon.knott on 06.12.2017.
 */
public class Main {
    static Wartezimmer wartezimmer = new Wartezimmer();
    public static void main(String... args) {
        // Frau Schmitz (gesetzlich) betritt das Wartezimmer.
        betreten(new Patient("Frau Schmitz", false));

        // Frau Müller (privat) betritt das Wartezimmer.
        betreten(new Patient("Frau Müller", true));
        // Herr Meyer (privat) betritt das Wartezimmer.
        betreten(new Patient("Frau Meyer", true));
        // "Der Nächste bitte."
        derNaechsteBitte();
        // Herr Schubert (gesetzlich) betritt das Wartezimmer.
        betreten(new Patient("Herr Schubert", false));
        // "Der Nächste bitte."
        derNaechsteBitte();
        // "Der Nächste bitte."
        derNaechsteBitte();
        // Frau Winter (gesetzlich) betritt das Wartezimmer.
        betreten(new Patient("Frau Winter", false));

        // Wieviele Personen sind noch im Wartezimmer?
        werWartetNoch();
    }

    private static void werWartetNoch() {
        System.out.println(wartezimmer.werIstNochDa());
    }

    private static void betreten(Patient p) {
        System.out.println("Patient betritt das Zimmer: " + p.getName());
        wartezimmer.betreten(p);
        werWartetNoch();
    }

    private static void derNaechsteBitte() {
        System.out.println(wartezimmer.naechsterPatient());
        wartezimmer.derNaechsteBitte();
    }
}
