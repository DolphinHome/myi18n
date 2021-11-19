package com.example.myi18n.common.enums;

import com.example.myi18n.common.contants.StrinfContants;

public  enum ExceptionEnums {

    SERVER_EXCEPTION("exception.@1");

    ExceptionEnums(String code) {
        this.code = code;
    }
    private String code ;

    public String getCode() {
        return StrinfContants.I18N_PREFIX + code + StrinfContants.I18N_POSTFIX ;
    }
}
