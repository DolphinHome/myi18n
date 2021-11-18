package com.example.myi18n.aspect;

import com.example.myi18n.common.base.ResultVO;
import com.example.myi18n.utils.JsonUtils;
import com.example.myi18n.utils.ParticipateUtils;
import com.example.myi18n.utils.I18nUtils;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Aspect
public class TranslationAspect {

    // 切点
    // 指定拦截的包路径
    @Pointcut("execution(* com.example.myi18n.controller.*.*(..))")
    private void pointcut() {}

    @Around("pointcut()")
    private Object around(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //IP地址
        String ipAddr = ParticipateUtils.getRemoteHost(request);
        String url = request.getRequestURL().toString();
        String reqParam = ParticipateUtils.preHandle(pjp,request);
        log.info("请求源IP:【{}】,请求URL:【{}】,请求参数:【{}】",ipAddr,url,reqParam);

        Object result= pjp.proceed();
        if (result instanceof ResultVO){
            ResultVO data = (ResultVO) result;
            Class arrayListClass = I18nUtils.getArrayListClass(data.getData());
            Class aClass = I18nUtils.getClass(data.getData());
            //获取redis 数据解析然后替换我们数据

            if (null != arrayListClass){

            }
            if (null != aClass){

            }
        }



        return null;
    }



}
