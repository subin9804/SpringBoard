package org.subin.bootBoard.commons;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends CommonException {
    public AuthorizationException() {
        super(bundleValidation.getString("UnAuthorization"), HttpStatus.UNAUTHORIZED);
    }

    public AuthorizationException(String message) {
        super(bundleValidation.getString("File.notYours"), HttpStatus.UNAUTHORIZED);
    }
}
