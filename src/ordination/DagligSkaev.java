package ordination;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DagligSkaev extends Ordination {
    //------------------------------------------------------------
    // composition 1-->0..* dosis
    private final ArrayList<Dosis> dosis = new ArrayList<>();
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
    // TODO

    public void opretDosis(LocalTime tid, double antal) {
        // TODO
        Dosis dosis1 = new Dosis(tid, antal);
        dosis.add(dosis1);
    }

    @Override
    public double samletDosis() {
        int samlet= 0;
        for (int i = 0; i <dosis.size() ; i++) {

        }
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return this.getClass().toString();
    }
}
