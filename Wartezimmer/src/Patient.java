/**
 * Created by simon.knott on 06.12.2017.
 */
public class Patient {
    private String name;
    private boolean privat;

    public Patient(String name, boolean privat) {
        this.name = name;
        this.privat = privat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivat() {
        return privat;
    }

    public void setPrivat(boolean privat) {
        this.privat = privat;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", privat=" + privat +
                '}';
    }
}
