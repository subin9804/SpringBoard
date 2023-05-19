package org.subin.bootBoard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.subin.bootBoard.entities.Member;
import org.subin.bootBoard.entities.QMember;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    // 아이디로 회원조회
    Member findByUserId(String userId);


    // 아이디로 회원 존재 유무 체크
    default boolean exists(String userId) {
        QMember member = QMember.member;

        return exists(member.userId.eq(userId));
    }
}
