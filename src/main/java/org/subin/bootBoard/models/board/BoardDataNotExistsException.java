package org.subin.bootBoard.models.board;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class BoardDataNotExistsException extends CommonException {
    public BoardDataNotExistsException() {
        super(bundleValidation.getString("Validation.boardData.notExists"), HttpStatus.BAD_REQUEST);
    }
}
