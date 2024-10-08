package id.my.hendisantika.springbootrestclient.service;

import id.my.hendisantika.springbootrestclient.converter.EmployeeConverter;
import id.my.hendisantika.springbootrestclient.dto.EmployeeDto;
import id.my.hendisantika.springbootrestclient.entity.Employee;
import id.my.hendisantika.springbootrestclient.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-client
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/08/24
 * Time: 20.20
 * To change this template use File | Settings | File Templates.
 */
@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeConverter.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeConverter.mapToEmployeeDto(savedEmployee);
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + employeeId)
                );

        return EmployeeConverter.mapToEmployeeDto(existingEmployee);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> EmployeeConverter.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + employeeDto.getId())
                );

        // convert EmployeeDto to Employee JPA entity
        Employee employee = EmployeeConverter.mapToEmployee(employeeDto);
        return EmployeeConverter.mapToEmployeeDto(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long employeeId) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + employeeId)
                );

        employeeRepository.deleteById(employeeId);
    }
}
