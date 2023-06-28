package net.lzzy.accountingserver.handlers;

import lombok.Getter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author hp
 *
 * Description: 以json形式输出异常信息
 * 这里的参数指定仅处理接口的异常
 * 嵌套类: ExceptionWrapper类仅在Handler中使用
 * Outer.new Inner()
 * Outer.new Inner()
 */
//创建异常处理类
@RestControllerAdvice(annotations = RestController.class)  //拦截
public class ApiExceptionHandler {
    @Getter
    public static class ExceptionWrapper{
        private final String desc;
        private final String message;
        private final StackTraceElement[] elements;
        //创建异常包装类
        public ExceptionWrapper(String desc, String message, StackTraceElement[] elements) {
            this.desc = desc;
            this.message = message;
            this.elements = elements;
        }
    }

    //异常处理方法
    @ExceptionHandler(Exception.class)
    public ExceptionWrapper handler(WebRequest request, Exception e){
        return new ExceptionWrapper(request.getDescription(true), e.getMessage(), e.getStackTrace());
    }
}
