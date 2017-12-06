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
        wartendePatienten.enqueue(neu);
    }

    public void derNaechsteBitte() {
        wartendePatienten.dequeue();
    }

    public String werIstNochDa() {
        Queue<Patient> temp = new Queue<>();
        StringBuilder builder = new StringBuilder();

        while(!wartendePatienten.isEmpty()) {
            builder.append(wartendePatienten.front());
            temp.enqueue(wartendePatienten.front());
            wartendePatienten.dequeue();
        }

        this.wartendePatienten = temp;

        return builder.toString();
    }
}
