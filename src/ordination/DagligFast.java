package ordination;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DagligFast extends Ordination {


    //------------------------------------------------------------
    //composition 1-->0..4 dosis
    private final Dosis[] dosis = new Dosis[4];

    //------------------------------------------------------------
    //liste med datoer for daglige dosis
    private final ArrayList<Dosis[]> dosisForEnPeriode = new ArrayList<>();

    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    //get metode for listen med daglige dosis
    public ArrayList<Dosis[]> getDosisForEnPeriode() {
        return new ArrayList<>(dosisForEnPeriode);
    }

    //her tilføjes en daglig dosis til listen
    public void addDagligDosisTilListe(Dosis[] liste) {
        dosisForEnPeriode.add(liste);
    }

    //------------------------------------------------------------
    //forbindelse mellem dosis og dagligfast
    public Dosis[] getDosis() {
        //returnerer måske en ny metode, hvis der sker fejl i controller så ret her
        return Arrays.copyOf(dosis, 4);
    }

    //index må ikke være over 4
    public Dosis createDosis(LocalTime tid, double antal, int index) {
        Dosis dosis1 = new Dosis(tid, antal);
        dosis[index] = dosis1;
        return dosis1;
    }

    //------------------------------------------------------------
    //nedarvede metoder til at beregne samlet dosis for henholdsvis en periode og samlet dosis for et døgn
    @Override
    public double samletDosis() {
        double samlet = 0;
        for (int i = 0; i < dosisForEnPeriode.size(); i++) {
            for (int j = 0; j < dosis.length; j++) {
                if (dosis[j] != null) {
                    samlet += dosis[j].getAntal();
                }
            }
        }
        return samlet;
    }

    @Override
    public double doegnDosis() {
        double samlet = 0;
        for (int i = 0; i < dosis.length; i++) {
            if (dosis[i] != null) {
                samlet += dosis[i].getAntal();
            }
        }
        return samlet;
    }

    @Override
    public String getType() {
        return this.getClass().toString();
    }

    // TODO
}
