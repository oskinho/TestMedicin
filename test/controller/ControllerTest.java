package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void opretPNOrdination() {
    }

    @Test
    void TC1_opretDagligFastOrdination_SlutdatoEfterStartdato() {
        //Arrange
        Controller c = Controller.getController();
        LocalDate startDato = LocalDate.of(2022, 9, 22);
        LocalDate slutDato = LocalDate.of(2022, 9, 29);
        Patient p1 = new Patient("310894-2314", "Jane Hansen", 70);
        Laegemiddel lgm1 = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        double morgenAntal = 3;
        double middagAntal = 2;
        double aftenAntal = 1;
        double natAntal = 1;

        //Act
        c.opretDagligFastOrdination(startDato, slutDato, p1, lgm1, morgenAntal, middagAntal, aftenAntal, natAntal);

        //Assert

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