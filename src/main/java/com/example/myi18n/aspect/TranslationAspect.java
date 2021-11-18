package com.example.myi18n.aspect;

import com.example.myi18n.common.base.ResultVO;
import com.example.myi18n.common.contants.I18nContants;
import com.example.myi18n.common.contants.RedisKeyContants;
import com.example.myi18n.entity.I18nAllocate;
import com.example.myi18n.service.redis.RedisService;
import com.example.myi18n.utils.JsonUtils;
import com.example.myi18n.utils.ParticipateUtils;
import com.example.myi18n.utils.I18nUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@Aspect
public class TranslationAspect {

    @Autowired
    private RedisService redisService;
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
        String language = request.getHeader("language") != null ?  request.getHeader("language") : I18nContants.DEFAULT_LANGUAGE  ;
        Object result= pjp.proceed();
        if (result instanceof ResultVO){
            ResultVO resultVO = (ResultVO) result;
            Class arrayListClass = I18nUtils.getArrayListClass(resultVO.getData());
            Class aClass = I18nUtils.getClass(resultVO.getData());
            //获取redis 数据解析然后替换我们数据
            Object data = resultVO.getData();
            String json = JsonUtils.toJSON(data);
            List<String> params = I18nUtils.findParams(json);
            StringBuilder dataStr = new StringBuilder(json);
            String rediseJavaJson = redisService.get(RedisKeyContants.LANGUAGE_JAVA);
            List<I18nAllocate> i18nAllocates = JsonUtils.toList(rediseJavaJson, I18nAllocate.class);
            if (null == i18nAllocates){
                return null;
            }
            StringBuilder returnBuilder = I18nUtils.dataProcess(language, dataStr, i18nAllocates, params);
            if (null != arrayListClass){
                Object[] arrayObjects = JsonUtils.toArray(returnBuilder.toString(), arrayListClass);
                resultVO.setData(arrayObjects);
            }
            if (null != aClass){
                Object obj = JsonUtils.toBean(returnBuilder.toString(), aClass);
                resultVO.setData(obj);
            }
        }
        return result;
    }



}
