package net.lzzy.accountingserver.service;

import net.lzzy.accountingserver.entities.Employee;
import net.lzzy.accountingserver.entities.WorkPosition;
import net.lzzy.accountingserver.repositories.EmployeeProjection;
import net.lzzy.accountingserver.repositories.EmployeeRepository;
import net.lzzy.accountingserver.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeesRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public boolean isEmployeeNumberOccupied(String employeeNumber) {
        return employeesRepository.countByEmployeeNumber(employeeNumber) > 0;
    }

    @Override
    public Employee check(String employeeNumber, String password) {
        return employeesRepository.findByEmployeeNumberAndPassword(employeeNumber,password);
    }

    @Override
    public Employee register(Employee employee) {
        return employeesRepository.save(employee);
    }

    @Override
    public String getSalt(String employeeNumber) {
        List<EmployeeProjection> users =  employeesRepository.findEmployeesByEmployeeNumber(employeeNumber);
        if (users == null || users.size() == 0){
            return "";
        }
        EmployeeProjection user = users.get(0);
        if (user == null){
            return "";
        }
        String salt = user.getSalt();
        return salt == null ? "" : salt;
    }

    @Override
    public Employee getById(UUID id) {
        return employeesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> get() {
        return employeesRepository.findAllByOrderByEmployeeNumberDesc();
    }

    @Override
    public List<Employee> get(int page, int size) {
        return employeesRepository.findAllByOrderByEmployeeNumberDesc(PageRequest.of(page, size));
    }

    @Override
    public Integer count() {
        return employeesRepository.countAllBy();
    }
}
