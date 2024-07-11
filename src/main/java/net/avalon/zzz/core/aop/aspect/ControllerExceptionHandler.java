package net.avalon.zzz.core.aop.aspect;


import jakarta.servlet.http.HttpServletResponse;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.generic.core.util.ResponseUtil;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @Author: Weiyin
 * @Create: 2023/2/24 - 17:29
 */
@RestControllerAdvice(basePackages = {"net.avalon.zzz.controller"})
public class ControllerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletResponse response){

        Map<String,String> ret = new HashMap<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for(FieldError error:exception.getFieldErrors()){
            ret.put(error.getField(),error.getDefaultMessage());
        }
        response.setStatus(400);
        response.setContentType("application/json;charset=UTF-8");
        return ResponseUtil.fail(AvalonStatus.FIELD_NOTVALID,ret);
    }
}
