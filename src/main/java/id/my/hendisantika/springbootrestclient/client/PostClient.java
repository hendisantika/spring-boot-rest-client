package id.my.hendisantika.springbootrestclient.client;

import id.my.hendisantika.springbootrestclient.dto.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/12/24
 * Time: 09.40
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PostClient {

    private static final Logger log = LoggerFactory.getLogger(PostClient.class);
    private final RestClient restClient;

    /*
     * The RestClient and RestClientCustomizer are created in Application.java
     * This is a workaround for the RestClientTest issue and more about that can be
     * found here https://github.com/spring-projects/spring-boot/issues/38832
     */
    public PostClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
