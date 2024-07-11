package net.avalon.zzz.core.aop.aspect;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.generic.core.util.R;
import net.avalon.generic.core.util.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Description: 异常处理、page、pageSize校验
 *
 * @Author: Weiyin
 * @Create: 2023/2/24 - 20:56
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) //值越低，优先级越高
public class ResponseAspect {

    /**
     * 1. page、pageSize校验
     * 2. 异常处理
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("net.avalon.zzz.core.aop.CommonPointCuts.controller()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        // 异常处理
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        R ret = null;
        try {
            ret = (R) pjp.proceed();
        } catch (AvalonException e) {
            ret = ResponseUtil.fail(e);
        } catch (Exception e) {
            ret = ResponseUtil.fail(AvalonStatus.INTERNAL_SERVER_ERR);
        }
        // 根据异常类型设置响应状态码
        AvalonStatus status = AvalonStatus.getByCode(ret.getCode());
        changeHttpStatus(status, response);

        return ret;
    }


    private void changeHttpStatus(AvalonStatus status, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        switch (status) {
            case CREATED:
                //201:
                response.setStatus(201);
//                response.setStatus(HttpServletResponse.SC_CREATED);
                break;

            case RESOURCE_ID_NOTEXIST:
                // 404：资源不存在
                response.setStatus(404);
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;

            case AUTH_INVALID_JWT:
            case AUTH_NEED_LOGIN:
            case AUTH_JWT_EXPIRED:
            case AUTH_INVALID_ACCOUNT:
//            case AUTH_NEED_LOGIN:
                // 401
                response.setStatus(401);
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                break;

            case INTERNAL_SERVER_ERR:
                // 500：数据库或其他严重错误
                response.setStatus(500);
//                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                break;

            case FIELD_NOTVALID:
            case IMG_FORMAT_ERROR:
            case IMG_SIZE_EXCEED:
            case PARAMETER_MISSED:
            case FORM_HAS_BEEN_SUBMITTED:
                // 400
                response.setStatus(400);
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                break;

            case AUTH_NOT_ALLOW:
            case RESOURCE_ID_OUTSCOPE:
            case FILE_NO_WRITE_PERMISSION:
                // 403
                response.setStatus(403);
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                break;
            default:
                response.setStatus(200);
//                response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
