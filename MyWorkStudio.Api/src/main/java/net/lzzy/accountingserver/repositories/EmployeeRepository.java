package net.lzzy.accountingserver.repositories;

import net.lzzy.accountingserver.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Employee findByEmployeeNumberAndPassword(String employeeNumber, String password);
    Integer countByEmployeeNumber(String userName);
    List<EmployeeProjection> findEmployeesByEmployeeNumber(String employeeNumber);

    List<Employee> findAllByOrderByEmployeeNumberDesc();
    List<Employee> findAllByOrderByEmployeeNumberDesc(Pageable pageable);  //分页
    Integer countAllBy();
}
