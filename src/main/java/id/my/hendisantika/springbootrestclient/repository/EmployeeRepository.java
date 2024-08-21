package id.my.hendisantika.springbootrestclient.repository;

import id.my.hendisantika.springbootrestclient.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.19
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
