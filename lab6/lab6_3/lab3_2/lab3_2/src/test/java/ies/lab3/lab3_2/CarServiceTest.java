package ies.lab3.lab3_2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.repository.CarRepository;
import ies.lab3.lab3_2.service.CarManagerService;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    
    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    public void setUp() {
        //these expectations provide an alternative to the use of the repository

        Car car = new Car("Sansung", "XPTO");
        car.setCarId(1l);

        Car camaro = new Car("Camaro", "Amarelo");
        camaro.setCarId(2l);

        Car fusca = new Car("Fusca", "Azul");
        fusca.setCarId(3l);

        List<Car> allCars = Arrays.asList(car, camaro, fusca);

        Mockito.when(carRepository.findByCarId(1l)).thenReturn(car);
        Mockito.when(carRepository.findByCarId(2l)).thenReturn(camaro);
        Mockito.when(carRepository.findByCarId(3l)).thenReturn(fusca);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);

    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        Optional<Car> found = service.getCarDetails(1l);
        assertThat(found.get().getCarId()).isEqualTo(1l);
        verifyFindByIdIsCalledOnce();
    }

    @Test
     void whenInValidId_thenCarShouldNotBeFound() {
        Optional<Car> fromDb = service.getCarDetails(-99L);
        System.out.println("fromDb: ");
        System.out.println(fromDb);

        verifyFindByIdIsCalledOnce();
        assertThat(fromDb.isPresent());
    }

    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        Car car = new Car("Sansung", "XPTO");
        Car camaro = new Car("Camaro", "Amarelo");
        Car fusca = new Car("Fusca", "Azul");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(car.getModel(), camaro.getModel(), fusca.getModel());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }
}
