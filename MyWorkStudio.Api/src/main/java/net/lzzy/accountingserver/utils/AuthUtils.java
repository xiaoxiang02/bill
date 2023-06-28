package net.lzzy.accountingserver.utils;

import net.lzzy.accountingserver.web.models.VmEmployee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author hp
 */
@Component
public class AuthUtils {
    public static final List<VmEmployee> EMPLOYEES = new ArrayList<>();

    private static int minutes;

    @Value("${app.over.minutes}")
    public static void setMinutes(int minutes) {
        AuthUtils.minutes = minutes;
    }


    public static String logout(String token){
        VmEmployee vmEmployee = EMPLOYEES.stream()
                .filter(u -> u.getToken().equals(token))
                .findAny()
                .orElse(null);
        if (vmEmployee == null){
            return "注销失败，没有发现登录的记录";
        }
        EMPLOYEES.remove(vmEmployee);
        return "注销成功";
    }

    /**
     * 根据token获取当前登陆用户
     * */
    public static VmEmployee getUser(String token){
        return EMPLOYEES.stream()
                .filter(u -> u.getToken().equals(token))
                .findAny()
                .orElse(null);
    }
}

