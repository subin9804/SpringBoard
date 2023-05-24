package org.subin.bootBoard.models.board.config;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class BoardConfigNotExistException extends CommonException {
    public BoardConfigNotExistException() {
        super(bundleValidation.getString("Validation.board.notExists"), HttpStatus.BAD_REQUEST);
    }
}
