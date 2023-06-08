package org.subin.bootBoard.models.board;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

/**
 * 비회원 비밀번호 미검증시 발생하는 예외
 */
public class GuestPasswordNotCheckedException extends CommonException {
    public GuestPasswordNotCheckedException() {
        super("GuestPw.notChecked", HttpStatus.UNAUTHORIZED);
    }
}
