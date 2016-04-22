package wang.ronnie.aspect;

import wang.ronnie.utils.Calendars;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2016/3/10-11:06.
 *
 * @author ronnie
 */
@Aspect
@Configuration
public class SearchDateAspect {

    @Pointcut("execution(* wang.ronnie.controller.*Controller.*()) && args(startDate,endDate)")
    private void searchDatePointcut(Date startDate, Date endDate) {

    }

    @Around(value = "searchDatePointcut(startDate,endDate)", argNames = "startDate,endDate")
    public Object dealSearchDate(ProceedingJoinPoint joinpoint, Date startDate, Date endDate) throws Throwable {

        Object[] args = joinpoint.getArgs();
        if (args[0] == null) {
            args[0] = Calendars.getTodayEleven();
        } else {
        }
        return joinpoint.proceed(args);
    }
}
