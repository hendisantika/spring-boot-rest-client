package id.my.hendisantika.springbootrestclient;

import id.my.hendisantika.springbootrestclient.repository.EmployeeRepository;
import id.my.hendisantika.springbootrestclient.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(
        properties = {
                "management.endpoint.health.show-details=always",
                "spring.datasource.url=jdbc:tc:mysql:9.1.0:///employeeDB"
        },
        webEnvironment = RANDOM_PORT
)
class SpringBootRestClientApplicationTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void deleteAll() {
        userRepository.deleteAll();
        employeeRepository.deleteAll();
    }

}
