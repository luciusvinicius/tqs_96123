package ies.lab3.lab3_2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataJpaTest limits the test scope to the data access context (no web environment loaded, for example)
 * tries to autoconfigure the database, if possible (e.g.: in memory db)
 */
@DataJpaTest
public class CarRepositoryTest {
    
    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository CarRepository;


    @Test
    void whenFindEmployedByExistingId_thenReturnCar() {
        Car car = new Car("Sansung", "XPTO");

        entityManager.persistAndFlush(car);

        Car fromDb = CarRepository.findByCarId(car.getCarId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getModel()).isEqualTo(car.getModel());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = CarRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car = new Car("Sansung", "XPTO");
        Car camaro = new Car("Camaro", "Amarelo");
        Car fusca = new Car("Fusca", "Azul");

        entityManager.persist(car);
        entityManager.persist(camaro);
        entityManager.persist(fusca);
        entityManager.flush();

        List<Car> allCars = CarRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(car.getModel(), camaro.getModel(), fusca.getModel());
    }
}
