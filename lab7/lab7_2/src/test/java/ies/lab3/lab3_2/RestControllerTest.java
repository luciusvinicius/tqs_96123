package ies.lab3.lab3_2;

import ies.lab3.lab3_2.controller.CarController;
import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.service.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(get("/car/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is("Sansung")))
                .andExpect(jsonPath("$.model", is("XPTO")));

        verify(service, times(1)).getCarDetails(car.getCarId());
    }

    @Test
    public void getAllCars_Success() throws Exception {
        Car car = new Car("Sansung", "XPTO");
        Car camaro = new Car("Camaro", "Amarelo");
        Car fusca = new Car("Fusca", "Azul");

        when(service.getAllCars()).thenReturn(Arrays.asList(car, camaro, fusca));

        mockMvc.perform(
                        get("/cars")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is("XPTO")))
                .andExpect(jsonPath("$[1].model", is("Amarelo")))
                .andExpect(jsonPath("$[2].model", is("Azul")));


        verify(service, times(1)).getAllCars();
    }
}
