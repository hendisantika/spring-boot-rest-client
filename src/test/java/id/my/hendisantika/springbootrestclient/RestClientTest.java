package id.my.hendisantika.springbootrestclient;

import id.my.hendisantika.springbootrestclient.dto.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.32
 * To change this template use File | Settings | File Templates.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestClientTest {
    private final RestClient restClient;

    public RestClientTest() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Order(1)
    @Test
    public void createEmployee() {
        EmployeeDto newEmployee = new EmployeeDto(null, "admin", "admin", "admin123@gmail.com");

        EmployeeDto savedEmployee = restClient.post()
                .uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newEmployee)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(savedEmployee.toString());
    }

    @Order(2)
    @Test
    public void getEmployeeById() {
        Long employeeId = 1L;

        EmployeeDto employeeDto = restClient.get()
                .uri("/api/employees/{id}", employeeId)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(employeeDto);
    }

    @Order(3)
    @Test
    public void updateEmployee() {
        Long employeeId = 1L;

        EmployeeDto updatedEmployee = new EmployeeDto();
        updatedEmployee.setFirstName("Ramesh");
        updatedEmployee.setLastName("Fadatare");
        updatedEmployee.setEmail("ramesh@gmail.com");

        EmployeeDto result = restClient.put()
                .uri("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedEmployee)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(result.toString());
    }
}
