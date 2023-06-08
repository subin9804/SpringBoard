package org.subin.bootBoard.models.board;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class GuestPasswordIncorrectException extends CommonException {

    public GuestPasswordIncorrectException() {
        super(bundleValidation.getString("GuestPw.incorrect"), HttpStatus.BAD_REQUEST);
    }
}
