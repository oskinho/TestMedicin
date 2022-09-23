package ordination;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class DagligSkaevTest {

  @Test
  @Order(1)
  void DagligSkaev_Constructor_TC1_slutdatoEfterStartdato() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);

    //Act
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);

    //Assert
    assertEquals(startdato, ds.getStartDen());
    assertEquals(slutdato, ds.getSlutDen());
    assertNotNull(ds);
  }

  @Test
  @Order(2)
  void DagligSkaev_Constructor_TC2_slutdatoLigStartdato() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 22);

    //Act
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);

    //Assert
    assertEquals(startdato, ds.getStartDen());
    assertEquals(slutdato, ds.getSlutDen());
    assertNotNull(ds);
  }

  @Test
  @Order(3)
  void DagligSkaev_samletDosis_TC1_HeleTal() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    ds.createDosis(LocalTime.of(8, 30),2);
    ds.createDosis(LocalTime.of(12, 50),4);
    ds.createDosis(LocalTime.of(23, 45),3);
    ds.createDosis(LocalTime.of(3, 5),1);

    //Act
    double expected = 80;
    double actual = ds.samletDosis();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  @Order(4)
  void DagligSkaev_samletDosis_TC2_KommaTal() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    ds.createDosis(LocalTime.of(8, 30),2);
    ds.createDosis(LocalTime.of(12, 50),3.2);
    ds.createDosis(LocalTime.of(23, 45),7);
    ds.createDosis(LocalTime.of(3, 5),0);

    //Act
    double expected = 97.6;
    double actual = ds.samletDosis();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  @Order(5)
  void DagligSkaev_samletDosis_TC3_SammeStartdatoOgSlutDato() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 22);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    ds.createDosis(LocalTime.of(8, 30),3);
    ds.createDosis(LocalTime.of(12, 50),6);
    ds.createDosis(LocalTime.of(23, 45),1);
    ds.createDosis(LocalTime.of(3, 5),0);

    //Act
    double expected = 10;
    double actual = ds.samletDosis();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  @Order(6)
  void DagligSkaev_doegnDosis_TC1_HeleTal() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    ds.createDosis(LocalTime.of(8, 30),2);
    ds.createDosis(LocalTime.of(12, 50),4);
    ds.createDosis(LocalTime.of(23, 45),3);
    ds.createDosis(LocalTime.of(3, 5),1);

    //Act
    double expected = 10;
    double actual = ds.doegnDosis();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  @Order(7)
  void DagligSkaev_doegnDosis_TC2_KommaTal() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    ds.createDosis(LocalTime.of(8, 30),2);
    ds.createDosis(LocalTime.of(12, 50),3.2);
    ds.createDosis(LocalTime.of(23, 45),7);
    ds.createDosis(LocalTime.of(3, 5),0);

    //Act
    double expected = 12.2;
    double actual = ds.doegnDosis();

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  @Order(8)
  void DagligSkaev_createDosis_TC1_normal() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    LocalTime tid = LocalTime.of(8, 30);
    double antal = 3;

    //Act
    Dosis dosis1 = ds.createDosis(tid,antal);

    //Assert
    assertNotNull(dosis1);
    assertTrue(ds.getDosis().contains(dosis1));
    assertEquals(tid, dosis1.getTid());
    assertEquals(antal, dosis1.getAntal());
  }

  @Test
  @Order(9)
  void DagligSkaev_createDosis_TC1_sammeTidSomTC2() {
    //Arrange
    LocalDate startdato = LocalDate.of(2022, 9, 22);
    LocalDate slutdato = LocalDate.of(2022, 9, 29);
    DagligSkaev ds = new DagligSkaev(startdato, slutdato);
    LocalTime tid = LocalTime.of(8, 30);
    double antal = 3;

    //Act
    Dosis dosis1 = ds.createDosis(tid,antal);

    //Assert
    assertNotNull(dosis1);
    assertTrue(ds.getDosis().contains(dosis1));
    assertEquals(tid, dosis1.getTid());
    assertEquals(antal, dosis1.getAntal());
  }
}