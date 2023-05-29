package org.subin.bootBoard.models.board;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.controllers.boards.BoardForm;

@Service
@RequiredArgsConstructor
public class BoardDataSaveService {

    private final BoardValidator validator;

    public void save(BoardForm boardForm) {
        //validator.check(boardForm);
    }
}