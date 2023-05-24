package org.subin.bootBoard.models.board.config;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.controllers.admins.BoardSearch;
import org.subin.bootBoard.entities.Board;
import org.subin.bootBoard.entities.QBoard;
import org.subin.bootBoard.repositories.BoardRepository;

import static org.springframework.data.domain.Sort.Order.desc;


/**
 * 게시판 설정 목록
 */
@Service
@RequiredArgsConstructor
public class BoardConfigListService {
    private final BoardRepository boardRepository;

    public Page<Board> gets(BoardSearch boardSearch) {
        QBoard board = QBoard.board;
        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = boardSearch.getPage();
        int limit = boardSearch.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        /** 검색 조건 처리 */
        String sopt = boardSearch.getSopt();
        String skey = boardSearch.getSkey();
        if(sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            skey = skey.trim();
            sopt = sopt.trim();

            if(sopt.equals("all")) {    // 통함 검색 - bid, bName
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(board.bId.contains(skey))
                        .or(board.bName.contains(skey));
                andBuilder.and(orBuilder);

            } else if(sopt.equals("bId")) { // 게시판 아이디 bId
                andBuilder.and(board.bId.contains(skey));

            } else if(sopt.equals("bName")) { // 게시판명 bName
                andBuilder.and(board.bName.contains(skey));
            }
        }


        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createAt")));
        Page<Board> data = boardRepository.findAll(andBuilder, pageable);

        return data;
    }
}
