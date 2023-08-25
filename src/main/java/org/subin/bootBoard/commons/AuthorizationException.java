package org.subin.bootBoard.commons;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends CommonException{
    public AuthorizationException() {
        super(bundleError.getString("UnAuthorization"), HttpStatus.UNAUTHORIZED);
    }

    public AuthorizationException(String code) {
        super(bundleError.getString(code), HttpStatus.UNAUTHORIZED);

    }
}
