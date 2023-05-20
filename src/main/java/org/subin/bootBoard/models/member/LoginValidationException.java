package org.subin.bootBoard.models.member;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

/**
 * 로그인 유효셩 검사시 예외
 */
public class LoginValidationException extends CommonException {

    private String field;

    public LoginValidationException(String code) {
        super(bundleValidation.getString(code), HttpStatus.BAD_REQUEST);
    }

    public LoginValidationException(String field, String code) {
        this(code);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
