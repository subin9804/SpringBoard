package org.subin.bootBoard.models.board.config;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class DuplicateBoardConfigException extends CommonException {
    public DuplicateBoardConfigException() {
        super("이미 등록된 게시판 입니다.", HttpStatus.BAD_REQUEST);
    }
}
