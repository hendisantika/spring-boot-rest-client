package id.my.hendisantika.springbootrestclient.client;

import id.my.hendisantika.springbootrestclient.dto.EmployeeDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RestClientService {
    private final RestClient restClient;

    public RestClientService() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    private void createEmployee() {
        EmployeeDto newEmployee = new EmployeeDto(null, "admin", "admin", "admin@gmail.com");

        EmployeeDto savedEmployee = restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newEmployee)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(savedEmployee.toString());
    }
}
