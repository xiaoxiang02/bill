package net.lzzy.accountingserver.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.lzzy.accountingserver.constant.ApiConstant;
import net.lzzy.accountingserver.constant.Messages;
import net.lzzy.accountingserver.entities.Employee;
import net.lzzy.accountingserver.entities.WorkPosition;
import net.lzzy.accountingserver.enums.GenderEnum;
import net.lzzy.accountingserver.service.EmployeeService;
import net.lzzy.accountingserver.service.WorkPositionService;
import net.lzzy.accountingserver.utils.AuthUtils;
import net.lzzy.accountingserver.utils.StringUtils;
import net.lzzy.accountingserver.web.models.ApiResult;
import net.lzzy.accountingserver.web.models.VmEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author hp
 */
@CrossOrigin
@RestController
@RequestMapping(ApiConstant.ROUTE_EMPLOYEE_ROOT)
public class EmployeeController {
    private final EmployeeService employeeService;
    private final WorkPositionService workPositionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, WorkPositionService workPositionService) {
        this.employeeService = employeeService;
        this.workPositionService = workPositionService;
    }

    @PostMapping(value = ApiConstant.ROUTE_EMPLOYEE_LOGIN, produces = ApiConstant.API_PRODUCES)
    public ApiResult login(@RequestBody JSONObject json){
        String employeeNumber = json.getString(ApiConstant.KEY_LOGIN_EMPLOYEE_NUMBER);
        String origin = json.getString(ApiConstant.KEY_LOGIN_PASSWORD);
        String salt = employeeService.getSalt(employeeNumber);
        Employee employee = employeeService.check(employeeNumber, StringUtils.md5Encode(origin, salt));
        //登录不成功
        if (employee == null){
            return new ApiResult(false, Messages.WRONG_PASSWORD.toString(), null);
        }
        //登录成功
        VmEmployee vmEmployee = VmEmployee.apiLogin(employee);
        return new ApiResult(true, Messages.SUCCESS.toString(), vmEmployee);
    }

    /**查询所有员工*/
    @GetMapping(ApiConstant.ROUTE_EMPLOYEE_ALL)
    public ApiResult getEmployee(){
        List<VmEmployee> employees = new ArrayList<>();
        employeeService.get().forEach(employee -> employees.add(VmEmployee.create(employee)));
        return new ApiResult(true, employees.size() + "", employees);
    }

    /**注销*/
    @PostMapping(ApiConstant.ROUTE_EMPLOYEE_LOGOUT)
    public ApiResult logout(@RequestParam String token){
        return new ApiResult(true, AuthUtils.logout(token), null);
    }

    /**检查工号是否存在(前端）*/
    @GetMapping(ApiConstant.ROUTE_EMPLOYEE_EXISTS)
    public ApiResult isUserExists(@PathVariable String employee){
        boolean exists = employeeService.isEmployeeNumberOccupied(employee);
        return new ApiResult(true, exists ? "工号已存在" : "", exists);
    }

    /**注册员工*/
    @PostMapping(ApiConstant.ROUTE_EMPLOYEE_REGISTER)
    public ApiResult register(@RequestBody JSONObject json){
        String employeeNumber = json.getString(ApiConstant.KEY_EMPLOYEE_EMPLOYEE_NUMBER);
        String username = json.getString(ApiConstant.KEY_EMPLOYEE_USER_NAME);
        String password = json.getString(ApiConstant.KEY_EMPLOYEE_PASSWORD);
        String workPositionId = json.getString(ApiConstant.KEY_EMPLOYEE_WORK_POSITION_ID);
        String jobPosition = json.getString(ApiConstant.KEY_EMPLOYEE_JOB_POSITION);
        Integer gender = json.getInteger(ApiConstant.KEY_EMPLOYEE_GENDER);
        String email = json.getString(ApiConstant.KEY_EMPLOYEE_EMAIL);
        String phone = json.getString(ApiConstant.KEY_EMPLOYEE_PHONE);
        String identityCardCode = json.getString(ApiConstant.KEY_EMPLOYEE_IDENTITY_CARD_CODE);
        Date birthDay = json.getDate(ApiConstant.KEY_EMPLOYEE_BIRTH_DAY);
        String address = json.getString(ApiConstant.KEY_EMPLOYEE_ADDRESS);
        String avatar = json.getString(ApiConstant.KEY_EMPLOYEE_AVATAR);
        String salt = UUID.randomUUID().toString();
        if (StringUtils.isEmpty(username)){
            username = employeeNumber;
        }
        if (StringUtils.isEmpty(employeeNumber) ||
                StringUtils.isEmpty(workPositionId) ||
                StringUtils.isEmpty(jobPosition) ||
                StringUtils.isEmpty(gender.toString()) ||
                StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(avatar) ||
                StringUtils.isEmpty(identityCardCode) ||
                StringUtils.isEmpty(birthDay.toString()) ||
                StringUtils.isEmpty(address) ||
                StringUtils.isEmpty(password)) {
            return new ApiResult(false, Messages.INCOMPLETE_INFO.toString(), null);
        }
        if (!StringUtils.validMail(email) || !StringUtils.validPhone(phone)){
            return new ApiResult(false, Messages.INVALID_FORMAT.toString(), null);
        }
        /**检查工号是否存在(服务端）*/
        if (employeeService.isEmployeeNumberOccupied(employeeNumber)) {
            return new ApiResult(false, "工号已存在", null);
        }
        WorkPosition workPosition = workPositionService.getById(UUID.fromString(workPositionId));
        if (workPosition == null){
            return new ApiResult(false, Messages.WRONG_ID.toString(), null);
        }
        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeNumber);
        employee.setWorkPosition(workPosition);
        employee.setUsername(username);
        employee.setJobPosition(jobPosition);
        employee.setIdentityCardCode(identityCardCode);
        employee.setAddress(address);
        employee.setBirthDay(birthDay);
        employee.setGender(GenderEnum.getEnum(gender));
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setAvatar(avatar);
        employee.setSalt(salt);
        employee.setPassword(StringUtils.md5Encode(password, salt));
        VmEmployee vmEmployee = VmEmployee.apiLogin(employeeService.register(employee));
        return new ApiResult(true, "注册员工成功",vmEmployee);
    }
}
