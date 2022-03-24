package ies.lab3.lab3_2;

import java.util.Arrays;
import java.util.List;

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
       
        List<Car> allCars = Arrays.asList(car, camaro, fusca);

        Mockito.when(carRepository.findByModel("Camaro")).thenReturn(camaro);
        //Mockito.when(carRepository.findByCarId(car.getCarId())).thenReturn(car);
        // Mockito.when(carRepository.findByName(alex.getName())).thenReturn(alex);
        // Mockito.when(carRepository.findByName("wrong_name")).thenReturn(null);
        // Mockito.when(carRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        // Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());

    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound() {
        
    }
}
