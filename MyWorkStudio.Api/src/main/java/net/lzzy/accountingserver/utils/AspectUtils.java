package net.lzzy.accountingserver.utils;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author hp
 */
public class AspectUtils {
    public static String getToken(ProceedingJoinPoint point){
        //获取用户token
        Object[] args = point.getArgs();
        String[] names = ((MethodSignature)point.getSignature()).getParameterNames();
        int i = 0;
        for (String name : names){
            if ("token".equals(name)){
                return args[i].toString();
            }
            i++;
        }
        JSONObject obj = (JSONObject) args[0];
        return obj.getString("token");
        //当添加其他类型的控制器方法参数时，需要处理对应类型的token
    }
}
