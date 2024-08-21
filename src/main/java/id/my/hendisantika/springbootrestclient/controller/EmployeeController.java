package id.my.hendisantika.springbootrestclient.controller;

import id.my.hendisantika.springbootrestclient.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.24
 * To change this template use File | Settings | File Templates.
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
}
