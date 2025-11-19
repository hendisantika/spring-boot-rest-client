package id.my.hendisantika.springbootrestclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.springbootrestclient.client.PostClient;
import id.my.hendisantika.springbootrestclient.dto.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/12/24
 * Time: 09.43
 * To change this template use File | Settings | File Templates.
 */
@RestClientTest(PostClient.class)
class PostClientTest {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    PostClient postClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllPosts() throws JsonProcessingException {
        // given
        List<Post> data = List.of(
                new Post(1, 1, "Hello, World!", "This is my first post!"),
                new Post(2, 1, "Testing Rest Client with @RestClientTest", "This is a post about testing RestClient calls")
        );

        // when
        this.server
                .expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));

        // then
        List<Post> posts = postClient.findAll();
        assertThat(posts.size()).isEqualTo(2);
    }
}
