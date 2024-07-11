package net.avalon.zzz.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Description:
 *
 * @Author: Weiyin
 * @Create: 2023/2/24 - 20:43
 */
@Aspect
public class CommonPointCuts {

    /**
     * match：所有控制器
     *
     * 1. 异常处理
     * 2. page、pageSize校验
     */
    @Pointcut("execution(* net.avalon.zzz.controller.*.*(..))")
    public void controller(){}
}
