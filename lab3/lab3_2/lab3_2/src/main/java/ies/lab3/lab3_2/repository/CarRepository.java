package ies.lab3.lab3_2.repository;

import java.util.List;
import ies.lab3.lab3_2.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>{
    
    public Car findByCarId(long id);
    public List<Car> findAll();
    public Car findByModel(String model);
}
