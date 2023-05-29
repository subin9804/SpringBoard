package org.subin.bootBoard.models.board;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

/**
 * 게시판 유효성 검사 관련 예외
 */
public class BoardValidationException extends CommonException {

    public BoardValidationException(String code) {
        super(bundleValidation.getString(code), HttpStatus.BAD_REQUEST);
    }
}
