package org.subin.bootBoard.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.entities.BoardData;

@Service
@RequiredArgsConstructor
public class GuestPasswordCheckService {

    private final BoardDataInfoService infoService;
    private final PasswordEncoder passwordEncoder;
    public void check(Long id, String password) {
        check(id, password, "board");
    }

    public void check(Long id, String password, String mode) {
        if(id == null) {
            throw new BoardValidationException("BadRequest");

        }
        
        mode = mode == null || mode.isBlank() ? "board" : mode;
        
        if(mode.equals("board")) {  // 일반 게시글
            BoardData boardData = infoService.get(id, "update");
            String guestPw = boardData.getGuestPw();
            boolean matched = passwordEncoder.matches(password, guestPw);
            if(!matched) {
                throw new GuestPasswordIncorrectException();
            }

        } else if (mode.equals("comment")) {    // 댓글

        }
    }
}
