package ies.lab3.lab3_2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.service.CarManagerService;

@RestController
public class CarController {

    @Autowired
    private CarManagerService service;
    

    @GetMapping("/car/{id}")
    public Optional<Car> getCar(@PathVariable(value="id") Long carId) {
        return service.getCarDetails(carId);
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @PostMapping("/car" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = service.save(car);
        return new ResponseEntity<>(saved, status);
    }
}
