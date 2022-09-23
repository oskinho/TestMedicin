package ordination;

import gui.TypeOrdination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class DagligSkaev extends Ordination {
    //------------------------------------------------------------
    // composition 1-->0..* dosis
    private final ArrayList<Dosis> dosis = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }
    //------------------------------------------------------------
    //forbindelse mellem dosis og dagligskæv

    public ArrayList<Dosis> getDosis() {
        return new ArrayList<>(dosis);
    }

    public Dosis createDosis(LocalTime tid, double antal) {
        Dosis dosis1 = new Dosis(tid, antal);
        dosis.add(dosis1);
        return dosis1;
    }

    //------------------------------------------------------------


    @Override
    public double samletDosis() {
        double samlet = 0;
        for (int i = 0; i < dosis.size(); i++) {
            samlet += dosis.get(i).getAntal();
        }
        return samlet*antalDage();
    }

    @Override
    public double doegnDosis() {
        double samlet = 0;
        for (int i = 0; i < dosis.size(); i++) {
            samlet += dosis.get(i).getAntal();
        }
        return samlet;

    }

    @Override
    public String getType() {
        return TypeOrdination.SKAEV.toString();
    }
}
