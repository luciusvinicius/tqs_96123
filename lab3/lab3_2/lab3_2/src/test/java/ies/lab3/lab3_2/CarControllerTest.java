package ies.lab3.lab3_2;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ies.lab3.lab3_2.controller.CarController;
import ies.lab3.lab3_2.model.Car;
import ies.lab3.lab3_2.service.CarManagerService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCar_thenCreateCar() throws IOException, Exception {
        Car car = new Car("Sansung", "XPTO");

        when(service.save(Mockito.any())).thenReturn(car);

        mvc.perform(
            post("/car").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.maker", is("Sansung")))
        .andExpect(jsonPath("$.model", is("XPTO")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    public void getCarById_Success() throws Exception {
        Car car = new Car("Sansung", "XPTO");

        System.out.println("carId: " + car.getCarId());

        when(service.getCarDetails(car.getCarId())).thenReturn(Optional.of(car));

        mvc.perform(
            get("/car/1")
        )
        .andExpect(status().isFound())
        .andExpect(jsonPath("$.maker", is("Sansung")))
        .andExpect(jsonPath("$.model", is("XPTO")));

        verify(service, times(1)).save(Mockito.any());
    }
}
