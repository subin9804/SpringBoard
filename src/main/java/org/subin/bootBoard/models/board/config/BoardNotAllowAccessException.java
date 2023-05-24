package org.subin.bootBoard.models.board.config;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class BoardNotAllowAccessException extends CommonException {
    public BoardNotAllowAccessException() {
        super(bundleValidation.getString("Validation.board.notAllowAccess"), HttpStatus.UNAUTHORIZED);
    }
}
