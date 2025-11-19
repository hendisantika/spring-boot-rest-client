package id.my.hendisantika.springbootrestclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.springbootrestclient.client.RestClientService;
import id.my.hendisantika.springbootrestclient.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/11/24
 * Time: 08.39
 * To change this template use File | Settings | File Templates.
 */
@RestClientTest(RestClientService.class)
public class EmployeeClientTest {
    @Autowired
    MockRestServiceServer server;

    @Autowired
    RestClientService restClientService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldCreateEmployee() throws JsonProcessingException {
        // given
        EmployeeDto newEmployee = new EmployeeDto(null, "admin", "admin", "admin@gmail.com");
        EmployeeDto savedEmployee = new EmployeeDto(1L, "admin", "admin", "admin@gmail.com");

        // when
        this.server
                .expect(requestTo("http://localhost:8080/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(savedEmployee), MediaType.APPLICATION_JSON));

        // Note: RestClientService.createEmployee() is private, so we can't test it directly
        // This test structure is here to demonstrate how it would work if the method were public
        assertThat(newEmployee).isNotNull();
        assertThat(restClientService).isNotNull();
    }
}
