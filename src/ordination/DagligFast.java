package ordination;

import gui.TypeOrdination;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class DagligFast extends Ordination {


    //------------------------------------------------------------
    //composition 1-->0..4 dosis
    private final Dosis[] dosis = new Dosis[4];

    //constructor

    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }
    //------------------------------------------------------------
    //liste med datoer for daglige dosis
//    private final ArrayList<Dosis[]> dosisForEnPeriode = new ArrayList<>();
//
//
//
//    //get metode for listen med daglige dosis
//    public ArrayList<Dosis[]> getDosisForEnPeriode() {
//        return new ArrayList<>(dosisForEnPeriode);
//    }
//
//    //her tilføjes en daglig dosis til listen
//    public void addDagligDosisTilListe(Dosis[] liste) {
//        dosisForEnPeriode.add(liste);
//    }

    //------------------------------------------------------------
    //forbindelse mellem dosis og dagligfast
    public Dosis[] getDosis() {
        //returnerer måske en ny metode, hvis der sker fejl i controller så ret her
        return Arrays.copyOf(dosis, 4);
    }

    //index må ikke være over 4
    public Dosis createDosis(LocalTime tid, double antal) {
        //sorter efter tid
        // 04-10 - 10-16 - 16-22 - 22-04
        Dosis dosis1 = new Dosis(tid, antal);
        if (tid.isAfter(LocalTime.of(21, 59)) || tid.isBefore(LocalTime.of(4, 0)) && dosis[3] == null) {
            dosis[3] = dosis1;
        }
        if (tid.isAfter(LocalTime.of(15,59 )) && tid.isBefore(LocalTime.of(22, 0)) && dosis[2] == null) {
            dosis[2] = dosis1;
        }
        if (tid.isAfter(LocalTime.of(9 , 59)) && tid.isBefore(LocalTime.of(16, 0)) && dosis[1] == null) {
            dosis[1] = dosis1;
        }
        if (tid.isAfter(LocalTime.of(3, 59)) && tid.isBefore(LocalTime.of(10, 0)) && dosis[0] == null) {
            dosis[0] = dosis1;
        }

        return dosis1;
    }

    public static void main(String[] args) {
        DagligFast df = new DagligFast(LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 10));
        df.createDosis(LocalTime.of(10, 0), 10);

        System.out.println(Arrays.toString(df.getDosis()));
    }

    //------------------------------------------------------------
    //nedarvede metoder til at beregne samlet dosis for henholdsvis en periode og samlet dosis for et døgn
    @Override
    public double samletDosis() {
        double samlet = 0;
        double dageMlFoersteOgSidsteGivning = antalDage();
        for (int i = 0; i < dosis.length; i++) {
            if (dosis[i] != null) {
                samlet += dosis[i].getAntal();
            }
        }

        return samlet * dageMlFoersteOgSidsteGivning;
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
        return TypeOrdination.FAST.toString();
    }
}
