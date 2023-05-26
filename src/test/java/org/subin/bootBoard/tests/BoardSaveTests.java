package org.subin.bootBoard.tests;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.subin.bootBoard.controllers.boards.BoardForm;
import org.subin.bootBoard.controllers.members.JoinForm;
import org.subin.bootBoard.entities.Board;
import org.subin.bootBoard.entities.Member;
import org.subin.bootBoard.models.board.BoardDataSaveService;
import org.subin.bootBoard.models.board.config.BoardConfigInfoService;
import org.subin.bootBoard.models.board.config.BoardConfigSaveService;
import org.subin.bootBoard.models.member.MemberSaveService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("게시글 등록, 수정 테스트")
public class BoardSaveTests {

    @Autowired
    private BoardDataSaveService saveService;

    @Autowired
    private BoardConfigSaveService configSaveService;

    @Autowired
    private BoardConfigInfoService configInfoService;

    @Autowired
    private MemberSaveService memberSaveService;

    private Board board;

    private JoinForm joinForm;

    @BeforeEach
    @Transactional
    void init() {
        // 게시판 설정 추가
        org.subin.bootBoard.controllers.admins.BoardForm boardForm = new org.subin.bootBoard.controllers.admins.BoardForm();
        boardForm.setBId("freetalk");
        boardForm.setBName("자유게시판");
        configSaveService.save(boardForm);
        board = configInfoService.get(boardForm.getBId(), true);

        // 회원 가입 추가
        joinForm = JoinForm.builder()
                .userId("userId")
                .userPw("aA!123456")
                .userPwRe("aA!123456")
                .email("user01@test.org")
                .userNm("사용자01")
                .mobile("01000000000")
                .agrees(new boolean[]{true})
                .build();
        memberSaveService.save(joinForm);
    }

    private BoardForm getGuestBoardForm() {
        return BoardForm.builder()
                .bId(board.getBId())
                .guestPw("123456")
                .poster("비회원!")
                .subject("제목!")
                .content("내용")
                .category(board.getCategories() == null ? null : board.getCategories()[0])
                .build();
    }


    //@WithMockUser(username="user01", password="aA!123456")
    private BoardForm getMemberBoardForm() {
      return BoardForm.builder()
              .poster(joinForm.getUserNm())
              .subject("제목!")
              .content("내용!")
              .category(board.getCategories() == null ? null : board.getCategories()[0])
              .build();
    }


    @Test
    @DisplayName("게시글 등록 성공시 예외 없음")
    void registerSuccessTest() {
        assertDoesNotThrow(() -> {
            saveService.save(getGuestBoardForm());
        });
    }



}
