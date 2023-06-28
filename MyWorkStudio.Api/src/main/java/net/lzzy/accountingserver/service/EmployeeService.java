package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Employee;

import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
public interface EmployeeService {

    boolean isEmployeeNumberOccupied(String employeeNumber);

    Employee check(String employeeNumber, String password);

    Employee register(Employee employees);

    String getSalt(String employeeNumber);

    Employee getById(UUID id);

    List<Employee> get();

    List<Employee> get(int page, int size);

    Integer count();
}
