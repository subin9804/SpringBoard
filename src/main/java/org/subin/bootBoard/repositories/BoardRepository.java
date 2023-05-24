package org.subin.bootBoard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.subin.bootBoard.entities.Board;

public interface BoardRepository extends JpaRepository<Board, String>, QuerydslPredicateExecutor<Board> {

}
