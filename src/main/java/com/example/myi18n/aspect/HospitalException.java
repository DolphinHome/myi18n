package com.example.myi18n.aspect;

import com.example.myi18n.common.base.ResultVO;
import com.example.myi18n.common.enums.ResultCode;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @name: HospitalException
 * @description: 全局异常处理
 * @type: JAVA
 * @since: 2020/11/5 20:24
 * @author: DuanLinPeng
 */
@RestControllerAdvice
public class HospitalException extends Exception {

    @ExceptionHandler(RuntimeException.class)
    public ResultVO<String> APIExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        // 注意哦，这里返回类型是自定义响应体
        return new ResultVO<>(ResultCode.ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 注意哦，这里返回类型是自定义响应体
        e.printStackTrace();
        return new ResultVO<>(ResultCode.ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<String> ExceptionDAY(Exception e){
        e.printStackTrace();
        return new ResultVO<>(ResultCode.ERROR,e.getMessage());
    }


}
