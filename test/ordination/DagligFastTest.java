package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void TC1_DagligFast_slutdatoEfterStartdato() {
        //Arrange
        LocalDate startDato = LocalDate.of(2022, 9, 22);
        LocalDate slutDato = LocalDate.of(2022, 9, 29);

        //Act
        DagligFast df = new DagligFast(startDato, slutDato);

        //Assert
        assertNotNull(df);
        assertEquals(startDato, df.getStartDen());
        assertEquals(slutDato, df.getSlutDen());
    }

    @Test
    void TC2_DagligFast_slutdatoPåStartdato() {
        //Arrange
        LocalDate startDato = LocalDate.of(2022, 9, 22);
        LocalDate slutDato = LocalDate.of(2022, 9, 22);

        //Act
        DagligFast df = new DagligFast(startDato, slutDato);

        //Assert
        assertNotNull(df);
        assertEquals(startDato, df.getStartDen());
        assertEquals(slutDato, df.getSlutDen());
    }

    @Test
    void TC1_samletDosis() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));
        df.createDosis(LocalTime.of(8, 0), 2);
        df.createDosis(LocalTime.of(12, 0), 3);
        df.createDosis(LocalTime.of(18, 0), 0);
        df.createDosis(LocalTime.of(23, 0), 1);

        //Act
        double expected = 48.0;
        double actual = df.samletDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void TC2_samletDosis() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));
        df.createDosis(LocalTime.of(8, 0), 4);
        df.createDosis(LocalTime.of(12, 0), 2);
        df.createDosis(LocalTime.of(18, 0), 5.4);
        df.createDosis(LocalTime.of(23, 0), 0);

        //Act
        double expected = 91.2;
        double actual = df.samletDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void TC3_samletDosis_SlutdatoOgStartdatoEns() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 22));
        df.createDosis(LocalTime.of(8, 0), 3);
        df.createDosis(LocalTime.of(12, 0), 6);
        df.createDosis(LocalTime.of(18, 0), 1);
        df.createDosis(LocalTime.of(23, 0), 2);

        //Act
        double expected = 12.0;
        double actual = df.samletDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void TC1_doegnDosis() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));
        df.createDosis(LocalTime.of(8, 0), 2);
        df.createDosis(LocalTime.of(12, 0), 3);
        df.createDosis(LocalTime.of(18, 0), 0);
        df.createDosis(LocalTime.of(23, 0), 1);

        //Act
        double expected = 6.0;
        double actual = df.doegnDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void TC2_doegnDosis() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));
        df.createDosis(LocalTime.of(8, 0), 4);
        df.createDosis(LocalTime.of(12, 0), 3);
        df.createDosis(LocalTime.of(18, 0), 1);
        df.createDosis(LocalTime.of(23, 0), 2.2);

        //Act
        double expected = 10.2;
        double actual = df.doegnDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void TC1_createDosis_MorgenDosis_kl8() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(8, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[0]);
        assertEquals(dosis.getTid(), df.getDosis()[0].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[0].getAntal());
    }

    @Test
    void TC2_createDosis_MiddagDosis_Grænseværdi_kl10() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(10, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[1]);
        assertEquals(dosis.getTid(), df.getDosis()[1].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[1].getAntal());
    }

    @Test
    void TC3_createDosis_MiddagDosis_kl13() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(13, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[1]);
        assertEquals(dosis.getTid(), df.getDosis()[1].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[1].getAntal());
    }

    @Test
    void TC4_createDosis_AftenDosis_Grænseværdi_kl16() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(16, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[2]);
        assertEquals(dosis.getTid(), df.getDosis()[2].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[2].getAntal());
    }

    @Test
    void TC5_createDosis_AftenDosis_kl18() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(18, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[2]);
        assertEquals(dosis.getTid(), df.getDosis()[2].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[2].getAntal());
    }

    @Test
    void TC6_createDosis_NatDosis_Grænseværdi_kl22() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(22, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[3]);
        assertEquals(dosis.getTid(), df.getDosis()[3].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[3].getAntal());
    }

    @Test
    void TC7_createDosis_NatDosis_kl01() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(1, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[3]);
        assertEquals(dosis.getTid(), df.getDosis()[3].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[3].getAntal());
    }

    @Test
    void TC8_createDosis_MorgenDosis_Grænseværdi_kl4() {
        //Arrange
        DagligFast df = new DagligFast(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29));

        //Act
        Dosis dosis = df.createDosis(LocalTime.of(4, 0), 2);

        //Assert
        assertNotNull(dosis);
        assertEquals(dosis, df.getDosis()[0]);
        assertEquals(dosis.getTid(), df.getDosis()[0].getTid());
        assertEquals(dosis.getAntal(), df.getDosis()[0].getAntal());
    }
}