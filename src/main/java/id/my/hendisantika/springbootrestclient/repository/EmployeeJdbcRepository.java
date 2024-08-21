package id.my.hendisantika.springbootrestclient.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.16
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeJdbcRepository {

    private final JdbcClient jdbcClient;
}
