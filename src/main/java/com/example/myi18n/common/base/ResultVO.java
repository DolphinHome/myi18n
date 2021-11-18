package com.example.myi18n.common.base;


import com.example.myi18n.common.enums.ResultCode;
import lombok.Data;

/**
 * @name: ResultVO
 * @description: XXX
 * @type: JAVA
 * @since: 2020/11/5 20:33
 * @author: DuanLinPeng
 */
@Data
public class ResultVO<T> {
    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;


    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

}
