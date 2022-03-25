package ies.lab3.lab3_2.service;

import java.util.List;
import java.util.Optional;

import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.repository.CarRepository;

public class CarManagerService {

    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return null;
    }

    public Optional<Car> getCarDetails(long id) {
        Car car = carRepository.findByCarId(id);

        return Optional.of(car);
    }
}
