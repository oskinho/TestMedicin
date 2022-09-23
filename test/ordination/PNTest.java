package ordination;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    //METODE GIVDOSIS TEST

    @org.junit.jupiter.api.Test
    void givDosis_IndenforIntervallet() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        boolean expected = true;
        //act
        boolean actual = pn.givDosis(LocalDate.of(2022, 9, 23));
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void givDosis_UdenforIntervallet() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 2), LocalDate.of(2022, 9, 29), 5);
        boolean expected = false;
        //act
        boolean actual = pn.givDosis(LocalDate.of(2022, 9, 30));
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void givDosis_DatoForStartOrdination() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        boolean expected = true;
        //act
        boolean actual = pn.givDosis(LocalDate.of(2022, 9, 22));
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void givDosis_DatoForSlutOrdination() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        boolean expected = true;
        //act
        boolean actual = pn.givDosis(LocalDate.of(2022, 9, 29));
        //assert
        assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------
    //METODE DØGNDOSIS TEST

    @org.junit.jupiter.api.Test
    void doegnDosis_1dag_5enheder() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        double expected = 5.0;
        //act
        double actual = pn.doegnDosis();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void doegnDosis_3_dage_5enheder() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 24));
        pn.givDosis(LocalDate.of(2022, 9, 25));
        double expected = 5.0;
        //act
        double actual = pn.doegnDosis();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void doegnDosis_5dage_5enheder() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 24));
        pn.givDosis(LocalDate.of(2022, 9, 25));
        pn.givDosis(LocalDate.of(2022, 9, 28));
        pn.givDosis(LocalDate.of(2022, 9, 29));
        double expected = 3.5714285714285716;
        //act
        double actual = pn.doegnDosis();
        //assert
        assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------
    //METODE SAMLETDOSIS TEST

    @org.junit.jupiter.api.Test
    void samletDosis_ForEnEnkeltDag_5_enheder() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        double expected = 5;
        //act
        double actual = pn.samletDosis();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void samletDosis_For3Dage_5_enheder() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 24));
        pn.givDosis(LocalDate.of(2022, 9, 25));
        double expected = 15;
        //act
        double actual = pn.samletDosis();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void samletDosis_For3Dage_5_enheder_2DosisSammeDag() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 25));
        double expected = 15;
        //act
        double actual = pn.samletDosis();
        //assert
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    //METODE GETANTALGANGEGIVET TEST

    @org.junit.jupiter.api.Test
    void getAntalGangeGivet_0() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);


        double expected = 0;
        //act
        double actual = pn.getAntalGangeGivet();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getAntalGangeGivet_1() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));

        double expected = 1;
        //act
        double actual = pn.getAntalGangeGivet();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getAntalGangeGivet_3() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 24));
        pn.givDosis(LocalDate.of(2022, 9, 25));
        double expected = 3;
        //act
        double actual = pn.getAntalGangeGivet();
        //assert
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getAntalGangeGivet_5() {
        //arrange
        PN pn = new PN(LocalDate.of(2022, 9, 22), LocalDate.of(2022, 9, 29), 5);
        pn.givDosis(LocalDate.of(2022, 9, 23));
        pn.givDosis(LocalDate.of(2022, 9, 24));
        pn.givDosis(LocalDate.of(2022, 9, 25));
        pn.givDosis(LocalDate.of(2022, 9, 28));
        pn.givDosis(LocalDate.of(2022, 9, 29));
        double expected = 5;
        //act
        double actual = pn.getAntalGangeGivet();
        //assert
        assertEquals(expected, actual);
    }
}