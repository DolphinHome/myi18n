package com.example.myi18n.aspect;

import com.example.myi18n.common.base.ResultVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * 统一响应处理器
 * 1 在每个responseBody的响应返回之前进行处理
 * 2 全局异常捕捉 统一返回格式
 *
 * @author wyh
 * @date 2020/11/30 17:39
 **/
@Slf4j
@ControllerAdvice
public class TipControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final Integer STATUS_404 = 404;
    public static final String ERROR_MSG_404 = "接口地址不存在";

    /**
     * 决定是否执行beforeBodyWrite()方法
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            return ResponseVo.failure();
        }
        //String类型需要特殊处理 手动转为json字符串
        if (o instanceof String) {
            return JsonUtil.toJson(ResponseVo.success(o));
        }
        if (o instanceof ResponseVo) {
            return o;
        }
        //boolean类型 返回对应的成功或失败
        if (o instanceof Boolean) {
            return ResponseVo.builder((Boolean) o);
        }
        //404时 返回特定信息
        if (is404(o)) {
            return ResponseVo.failure(ERROR_MSG_404);
        }
        return ResponseVo.success(o);
    }

    /**
     * 全局异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVo<String> handler(Exception e) {
        //default error message
        String msg = "系统内部出错";
        log.error(msg, e);
        return ResponseVo.failure(msg);
    }


    /**
     * 参数校验异常异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseVo<String> handlerConstraintViolationException(Exception e) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
        String msg = StringUtils.collectionToCommaDelimitedString(
                constraintViolationException.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList()));
        return ResponseVo.failure(msg);
    }


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseVo<String> handlerMethodArgumentNotValidException(Exception e) {
        StringBuilder message = new StringBuilder();
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                message.append(StrUtil.toUnderlineCase(fieldError.getField())).append(":").append(fieldError.getDefaultMessage()).append(",");
            } else {
                message.append(objectError.getDefaultMessage()).append(",");
            }

        }
        return ResponseVo.failure(message.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResponseVo<String> handlerBindException(Exception e) {
        BindException bindException = (BindException) e;
        String msg = StringUtils.collectionToCommaDelimitedString(
                bindException.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
        return ResponseVo.failure(msg);
    }

    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseVo<String> handlerMissingServletRequestParameterException(Exception e) {
        return ResponseVo.failure("缺少必填参数");
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseVo<String> handlerHttpMessageNotReadableException(Exception e) {
        return ResponseVo.failure("请求参数异常");
    }

    @ResponseBody
    @ExceptionHandler(value = ParamErrorException.class)
    public ResponseVo<String> handlerParamError(Exception e) {
        if (StrUtil.isBlank(e.getMessage())) {
            return ResponseVo.failure("参数错误");
        } else {
            return ResponseVo.failure(e);
        }
    }

    @ResponseBody
    @ExceptionHandler(value = TipException.class)
    public ResultVO<String> handlerTip(Exception e) {
        return ResultVO.failure(e);
    }

//    private boolean is404(Object o) {
//        if (o instanceof Map) {
//            Map<String, Object> map = Convert.toMap(String.class, Object.class, o);
//            Integer status = Convert.toInt(map.get("status"));
//            return STATUS_404.equals(status);
//        }
//        return false;
//    }
}