package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> datoerForDosis = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, int antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
    }


    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean result = false;
        if (givesDen.isAfter(super.getStartDen().minusDays(1)) && givesDen.isBefore(super.getSlutDen().plusDays(1))) {
            datoerForDosis.add(givesDen);
            result =  true;
        }
        return result;
    }

    /**
     * Returnerer (antal gange ordinationen er anvendt * antal enheder) / (antal dage mellem første og sidste givning)
     * @return
     */
    public double doegnDosis() {
        int dageMlFoersteOgSidsteGivning =  (int) ChronoUnit.DAYS.between(datoerForDosis.get(0), datoerForDosis.get(datoerForDosis.size() - 1));
        return (datoerForDosis.size() * antalEnheder)/dageMlFoersteOgSidsteGivning;
    }

    @Override
    public String getType() {
        return this.getClass().toString();
    }


    public double samletDosis() {
        return datoerForDosis.size() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return datoerForDosis.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public ArrayList<LocalDate> getDatoerForDosis() {
        return new ArrayList<>(getDatoerForDosis());
    }

}
