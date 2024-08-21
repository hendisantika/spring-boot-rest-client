package id.my.hendisantika.springbootrestclient.converter;

import id.my.hendisantika.springbootrestclient.dto.EmployeeDto;
import id.my.hendisantika.springbootrestclient.entity.Employee;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.23
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeConverter {
    // convert Employee JPA entity to EmployeeDto
    // convert EmployeeDto to Employee JPA entity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        return employee;
    }

    // convert Employee JPA entity to EmployeeDto
    // convert EmployeeDto to Employee JPA entity
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDto;
    }
}
