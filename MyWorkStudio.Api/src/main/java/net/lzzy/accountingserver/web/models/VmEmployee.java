package net.lzzy.accountingserver.web.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.lzzy.accountingserver.entities.Employee;
import net.lzzy.accountingserver.enums.GenderEnum;
import net.lzzy.accountingserver.utils.AuthUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author hp
 */
@Getter
@Setter
public class VmEmployee {
    private UUID id;
    private String employeeNumber;
    private String username;
    private UUID workPositionId;
    private String jobPosition;
    private GenderEnum gender;
    private String email;
    private String phone;
    private String identityCardCode;
    private Date birthDay;
    private String address;
    private String avatar;
    private String token;
    @JsonFormat(timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    private VmEmployee(){};

    private VmEmployee(Employee employee){
        id = employee.getId();
        employeeNumber = employee.getEmployeeNumber();
        username = employee.getUsername();
        workPositionId = employee.getWorkPosition().getId();
        jobPosition = employee.getWorkPosition().getWorkName();
        gender = employee.getGender();
        email = employee.getEmail();
        phone = employee.getPhone();
        identityCardCode = employee.getIdentityCardCode();
        birthDay = employee.getBirthDay();
        address = employee.getAddress();
        avatar = employee.getAvatar();
        lastLogin = new Date();
        token = UUID.randomUUID().toString();
        AuthUtils.EMPLOYEES.add(this);
    }

    public static VmEmployee create(Employee employee){
        VmEmployee vmEmployee = new VmEmployee();
        vmEmployee.setId(employee.getId());
        vmEmployee.setEmployeeNumber(employee.getEmployeeNumber());
        vmEmployee.setUsername(employee.getUsername());
        vmEmployee.setWorkPositionId(employee.getWorkPosition().getId());
        vmEmployee.setJobPosition(employee.getWorkPosition().getWorkName());
        vmEmployee.setGender(employee.getGender());
        vmEmployee.setEmail(employee.getEmail());
        vmEmployee.setPhone(employee.getPhone());
        vmEmployee.setIdentityCardCode(employee.getIdentityCardCode());
        vmEmployee.setBirthDay(employee.getBirthDay());
        vmEmployee.setAddress(employee.getAddress());
        vmEmployee.setAvatar(employee.getAvatar());
        vmEmployee.setToken("");
        return vmEmployee;
    }

    public static VmEmployee apiLogin(Employee employee){  //登录用
        VmEmployee vmEmployee = AuthUtils.EMPLOYEES
                .stream()
                .filter(u -> u.employeeNumber.equals(employee.getEmployeeNumber()))
                .findFirst()
                .orElse(null);
        if (vmEmployee == null){
            vmEmployee = new VmEmployee(employee);
        } else {
            vmEmployee.lastLogin = new Date();
        }
        return vmEmployee;
    }
}
