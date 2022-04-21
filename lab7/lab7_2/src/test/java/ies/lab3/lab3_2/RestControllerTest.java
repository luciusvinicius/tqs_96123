package ies.lab3.lab3_2;

import ies.lab3.lab3_2.controller.CarController;
import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.service.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class RestControllerTest {

    @MockBean
    CarManagerService service;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void getCarById_Success() throws Exception {
        Car car = new Car("Sansung", "XPTO");
        car.setCarId(1l);

        when(service.getCarDetails(car.getCarId())).thenReturn(Optional.of(car));

        RestAssuredMockMvc.given().contentType("application/json")
                .get("/car/1")
                .then()
                .statusCode(200).and()
                .body("maker", equalTo("Sansung")).and()
                .body("model", equalTo("XPTO"));

        verify(service, times(1)).getCarDetails(car.getCarId());
    }

    @Test
    public void getAllCars_Success() throws Exception {
        Car car = new Car("Sansung", "XPTO");
        Car camaro = new Car("Camaro", "Amarelo");
        Car fusca = new Car("Fusca", "Azul");

        when(service.getAllCars()).thenReturn(Arrays.asList(car, camaro, fusca));

        RestAssuredMockMvc.given().contentType("application/json")
                .get("/cars")
                .then().statusCode(200).and()
                .body("$", hasSize(3)).and()
                .body("[0].model", is("XPTO")).and()
                .body("[1].model", is("Amarelo")).and()
                .body("[2].model", is("Azul"));

        verify(service, times(1)).getAllCars();
    }

    @Test
    public void whenPostCar_thenCreateCar() throws IOException, Exception {
        Car car = new Car("Sansung", "XPTO");
        car.setCarId(1l);

        when(service.save(Mockito.any())).thenReturn(car);

        RestAssuredMockMvc.given().contentType("application/json")
                .body(JsonUtils.toJson(car))
                .when()
                .post("/car")
                .then().statusCode(201).and()
                .body("model", is("XPTO")).and()
                .body("maker", is("Sansung"));

        verify(service, times(1)).save(Mockito.any());
    }
}
