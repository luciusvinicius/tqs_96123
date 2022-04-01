package ies.lab3.lab3_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.repository.CarRepository;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(long id) {
        Car car = carRepository.findByCarId(id);

        return car != null ? Optional.of(car) : Optional.empty();
    }
}
