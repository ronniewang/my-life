package com.test.aspect;

import com.test.utils.Calendars;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 2016/3/10-11:06.
 *
 * @author ronnie
 */
@Aspect
@Configuration
public class LoggerAspect {

    public static AtomicLong requestId = new AtomicLong(System.currentTimeMillis());

    private static ThreadLocal<Long> currentRequestId = new ThreadLocal<>();

    public static Long getCurrentRequestId() {

        return currentRequestId.get();
    }

    @Pointcut("execution(* wang.ronnie.controller.*Controller.*()) && args(startDate,endDate)")
    private void searchDatePointcut(Date startDate, Date endDate) {

    }

    @Pointcut("execution(* wang.ronnie.controller.*Controller.*(*))")
    private void log() {

    }

    @Around(value = "log()")
    public Object log(ProceedingJoinPoint joinpoint) throws Throwable {

        long start = System.currentTimeMillis();
        long value = requestId.incrementAndGet();
        currentRequestId.set(value);
        long end;
        try {
            Object result = joinpoint.proceed();
            end = System.currentTimeMillis();
            System.out.println("[request id: " + value + "][api mills: " + (end - start) + "]");
            return result;
        } catch (Throwable throwable) {
            end = System.currentTimeMillis();
            System.out.println("[request id: " + value + "][api mills: " + (end - start) + "]");
            throw throwable;
        }
    }

    @Around(value = "searchDatePointcut(startDate,endDate)", argNames = "startDate, endDate")
    public Object dealSearchDate(ProceedingJoinPoint joinpoint, Date startDate, Date endDate) throws Throwable {

        Object[] args = joinpoint.getArgs();
        if (args[0] == null) {
            args[0] = Calendars.getTodayEleven();
        } else {
        }
        return joinpoint.proceed(args);
    }
}
