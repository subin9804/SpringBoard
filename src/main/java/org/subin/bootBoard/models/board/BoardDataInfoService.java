package org.subin.bootBoard.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.entities.Board;
import org.subin.bootBoard.entities.BoardData;
import org.subin.bootBoard.models.board.config.BoardConfigInfoService;
import org.subin.bootBoard.repositories.BoardDataRepository;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final BoardDataRepository boardDataRepository;
    private final BoardConfigInfoService configInfoService;

    public BoardData get(Long id) {
        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardDataNotExistsException::new);

        // 게시판 설정 조회 + 접근 권한 체크
        configInfoService.get(boardData.getBoard().getBId(), "view");

        return boardData;
    }
}
