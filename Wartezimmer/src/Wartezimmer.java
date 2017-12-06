/**
 * Created by simon.knott on 06.12.2017.
 */
public class Wartezimmer {
    Queue<Patient> wartendePatienten = new Queue<>();

    public int anzahlWartend() {
        Queue<Patient> temp = new Queue<>();
        int i = 0;

        while(!wartendePatienten.isEmpty()) {
            temp.enqueue(wartendePatienten.front());
            i++;
            wartendePatienten.dequeue();
        }

        return i;
    }

    public Patient naechsterPatient() {
        return wartendePatienten.front();
    }

    public void betreten(Patient neu) {
        System.out.println(neu.getName() + " hat das Wartezimmer betreten.");
        wartendePatienten.enqueue(neu);
    }

    public void derNaechsteBitte() {
        System.out.println(wartendePatienten.front().getName() + " verlässt das Wartezimmer.");
        wartendePatienten.dequeue();
    }

    public String werIstNochDa() {
        Queue<Patient> temp = new Queue<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Es warten noch: ");

        while(!wartendePatienten.isEmpty()) {
            builder.append(wartendePatienten.front().getName()).append(", ");
            temp.enqueue(wartendePatienten.front());
            wartendePatienten.dequeue();
        }

        this.wartendePatienten = temp;
        builder.replace(builder.length() - 2, builder.length(), ".");

        System.out.println(builder.toString());

        return builder.toString();
    }
}
