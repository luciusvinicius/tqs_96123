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
        Car camaro = new Car("Camaro", "Amarelo");
        Car fusca = new Car("Fusca", "Azul");
       
        System.out.println("Camaro ID: " + camaro.getCarId());
        System.out.println("Car ID: " + car.getCarId());
        System.out.println("Fusca ID: " + fusca.getCarId());

        List<Car> allCars = Arrays.asList(car, camaro, fusca);

        Mockito.when(carRepository.findByModel("Camaro")).thenReturn(camaro);
        Mockito.when(carRepository.findByCarId(0l)).thenReturn(car);
        Mockito.when(carRepository.findByCarId(1l)).thenReturn(camaro);
        Mockito.when(carRepository.findByCarId(2l)).thenReturn(fusca);
        // Mockito.when(carRepository.findByName(alex.getName())).thenReturn(alex);
        // Mockito.when(carRepository.findByName("wrong_name")).thenReturn(null);
        // Mockito.when(carRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        // Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());

    }

    @Test
    void findByCarId_Success() {
        Optional<Car> found = service.getCarDetails(1l);

        assertThat(found.get().getCarId()).isEqualTo(1l);
    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        
    }
}
