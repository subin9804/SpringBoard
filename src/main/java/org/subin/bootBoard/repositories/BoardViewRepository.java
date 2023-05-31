package org.subin.bootBoard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.subin.bootBoard.entities.BoardView;
import org.subin.bootBoard.entities.BoardViewId;
import org.subin.bootBoard.entities.QBoardView;

public interface BoardViewRepository extends JpaRepository<BoardView, BoardViewId>, QuerydslPredicateExecutor<BoardView> {

    /**
     * 게시글별 조회수 조회
     * @param id
     * @return
     */
    default long getHit(Long id) {
        QBoardView boardView = QBoardView.boardView;
        return count(boardView.id.eq(id));

    }

}
