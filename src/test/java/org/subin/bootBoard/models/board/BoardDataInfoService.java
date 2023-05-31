package org.subin.bootBoard.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.entities.BoardData;
import org.subin.bootBoard.repositories.BoardDataRepository;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long id) {
        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardDataNotExistsException::new);

        return null;
    }
}
