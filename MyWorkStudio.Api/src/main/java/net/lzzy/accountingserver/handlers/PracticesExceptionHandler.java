package net.lzzy.accountingserver.handlers;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hp
 */
@ControllerAdvice
public class PracticesExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView businessExceptionHandler(HttpServletRequest request, BusinessException e){
        String errInfo = "业务异常："+e.getErrInfo();
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", errInfo);
        mv.addObject("url", request.getRequestURI().toString());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }

    //第二种异常
    //第三种异常......


    //可以捕获所有异常
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "非业务异常");
        mv.addObject("url",request.getRequestURI().toString());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
