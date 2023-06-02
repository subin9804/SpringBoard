package org.subin.bootBoard.models.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.commons.MemberUtil;
import org.subin.bootBoard.entities.BoardData;
import org.subin.bootBoard.entities.BoardView;
import org.subin.bootBoard.repositories.BoardDataRepository;
import org.subin.bootBoard.repositories.BoardViewRepository;

import java.util.Objects;

/**
 * 조회수 업데이트
 */
@Service
@RequiredArgsConstructor
public class UpdateHitService {
    private final BoardViewRepository boardViewRepository;
    private final BoardDataRepository boardDataRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;

    public void update(Long id) {
        try {
            BoardView boardView = new BoardView();
            boardView.setId(id);
            boardView.setUid(""+getUid());
            boardViewRepository.saveAndFlush(boardView);

        } catch (Exception e) {}

        long cnt = boardViewRepository.getHit(id);
        BoardData boardData = boardDataRepository.findById(id).orElse(null);
        if(boardData != null) {
            boardData.setHit((int)cnt);
            boardDataRepository.flush();
        }
    }

    private int getUid() {
        String ip = request.getRemoteAddr();
        String ua = request.getHeader("User-Agent");

        return memberUtil.isLogin() ? memberUtil.getMember().getUserNo().intValue() : Objects.hash(ip, ua);
    }

}
