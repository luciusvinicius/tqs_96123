package ies.lab3.lab3_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureTestDatabase
// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@TestPropertySource( locations = "application-integrationtest.properties")
public class CarRepositoryIT {

    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
     void whenValidInput_thenCreateCar() {
        Car car = new Car("Sansung", "XPTO");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/car", car, Car.class);

        List<Car> found = repository.findAll();

        assertThat(found).extracting(Car::getModel).containsOnly("XPTO");
    }

    @Test
     void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("Sansung", "XPTO");
        createTestCar("Camaro", "Amarelo");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Sansung", "Camaro");

    }


    private void createTestCar(String name, String email) {
        Car emp = new Car(name, email);
        repository.saveAndFlush(emp);
    }
    
}
