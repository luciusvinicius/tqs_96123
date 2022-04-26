package tqsdemo.employeemngr.employee;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.web.util.UriComponentsBuilder;
import tqsdemo.employeemngr.data.Employee;
import tqsdemo.employeemngr.data.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContainerTest {

    // container
    @Container
    private static PostgreSQLContainer postgree = new PostgreSQLContainer("postgres:12")
            .withUsername("lucius")
            .withPassword("sussypass")
            .withDatabaseName("books");


//    @LocalServerPort
//    int localPort;
    Employee emp1, emp2;

    @Autowired
    private EmployeeRepository repository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgree::getJdbcUrl);
        registry.add("spring.datasource.password", postgree::getPassword);
        registry.add("spring.datasource.username", postgree::getUsername);
    }

    @BeforeEach
    void startEmployees() {
        emp1 = new Employee("Lucius", "lucius@ua.pt");
        emp2 = new Employee("Vinicius", "viniciusf@ua.pt");
    }

    @Test
    @Order(1)
    void whenGetEmployeeId_thenApiReturnsOneEmployee() {
//        String url = UriComponentsBuilder.newInstance()
//                .scheme("http")
//                .host("127.0.0.1")
//                .port(localPort)
//                .pathSegment("api","emplooye")
//                .toUriString();
        System.out.println("Sussy order 1!");
        repository.saveAndFlush(emp1);

        Employee response = repository.findById(emp1.getId()).get();
        assertEquals(response.getName(), emp1.getName());
    }

    @Test
    @Order(2)
    void getAllEmployees() {
        System.out.println("Sussy order 2");
        List<Employee> response = repository.findAll();
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

}
