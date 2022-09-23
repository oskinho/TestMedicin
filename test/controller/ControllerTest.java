package controller;

import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    //------------------------------------------------------------------
    @Test
    void opretPNOrdinationIndenforPnOrdination() {
        //arrange


        Laegemiddel lgm1 = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Patient p1 = new Patient("3108942314", "“Jane Hansen”", 70);
        //act
        PN c = Controller.getTestController().opretPNOrdination(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), p1, lgm1, 5);
        LocalDate expectedstart = LocalDate.of(2022, 9, 22);
        LocalDate expectedslut = LocalDate.of(2022, 9, 29);
        double antalenheder = 5;
        String expectedlægemiddel = "Paracetamol";

        //assert
        assertEquals(expectedstart, c.getStartDen());
        assertEquals(expectedslut, c.getSlutDen());
        assertEquals(antalenheder, c.getAntalEnheder());
        assertEquals(expectedlægemiddel, c.getLaegemiddel().getNavn());
        assertEquals(expectedlægemiddel, p1.getOrdinations().get(0).getLaegemiddel().getNavn());

    }

    @Test
    void opretPNOrdinationUdenforPnOrdination() {
//arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 21), 5);
        Laegemiddel lgm1 = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Patient p1 = new Patient("3108942314", "“Jane Hansen”", 70);
        //act
        pn.setLaegemiddel(lgm1);
        p1.addOrdination(pn);
        //assert
    }

    //------------------------------------------------------------------

    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkaevOrdination() {
    }

    @Test
    void ordinationPNAnvendt() {
    }

    @Test
    void anbefaletDosisPrDoegn() {
    }
}