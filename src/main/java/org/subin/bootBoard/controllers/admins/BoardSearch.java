package org.subin.bootBoard.controllers.admins;

import lombok.Data;


/**
 * 게시판 설정 검색
 */
@Data
public class BoardSearch {
    private int page = 1;
    private int limit = 20;     // 한 페이지 당 게시글 갯수

    private String sopt;    // 검색 조건
    private String skey;    // 검색 키워드
}
