package ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;
    // association: --> 0..* Ordination
    private final ArrayList<Ordination> ordinations = new ArrayList<>();

    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }

    // -------------------------------------------------------------------------

    public ArrayList<Ordination> getOrdinations() {
        return new ArrayList<>(ordinations);
    }

    /**
     * Adds the ordination to this patient,
     * if they aren't connected.
     * Pre: The ordination isn't connected to another patient.
     */
    public void addOrdination(Ordination ordination) {
        if (!ordinations.contains(ordination)) {
            ordinations.add(ordination);
        }
    }

    /**
     * Removes the ordination from this patient,
     * if they are connected.
     */
    public void removeOrdination(Ordination ordination) {
        if (ordinations.contains(ordination)) {
            ordinations.remove(ordination);
        }
    }

    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}
