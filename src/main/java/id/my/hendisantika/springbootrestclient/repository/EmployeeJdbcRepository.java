package id.my.hendisantika.springbootrestclient.repository;

import id.my.hendisantika.springbootrestclient.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

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

    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcClient.sql(sql).query(Employee.class).list();
    }

    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        return jdbcClient.sql(sql).param("id", id).query(Employee.class).optional();
    }

    @Transactional
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employees(first_name, last_name, email) VALUES(:first_name,:last_name,:email)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("first_name", employee.getFirstName())
                .param("last_name", employee.getLastName())
                .param("email", employee.getEmail())
                .update(keyHolder);
        BigInteger id = keyHolder.getKeyAs(BigInteger.class);
        employee.setId(id.longValue());
        return employee;
    }

    @Transactional
    public Employee update(Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        int count = jdbcClient.sql(sql)
                .param(1, employee.getFirstName())
                .param(2, employee.getLastName())
                .param(3, employee.getEmail())
                .param(4, employee.getId())
                .update();
        if (count == 0) {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }
}
