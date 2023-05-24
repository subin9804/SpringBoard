package org.subin.bootBoard.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.subin.bootBoard.commons.constants.Role;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Board extends BaseMemberEntity {
    @Id
    @Column(length=30)
    private String bId; // 게시판 ID

    @Column(length=60, nullable=false)
    private String bName;   // 게시판명

    @Column(name="isAvail")
    private boolean avail;    // 사용 여부

    private int rowsOfPage = 20;    // 1페이지 당 게시글 수

    private boolean showViewList;   // 게시글 하단 목록 노출

    @Lob
    private String category;    // 게시판 분류

    // 목록 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role listAccessRole = Role.ALL;

    // 글보기 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role viewAccessRole = Role.ALL;

    // 글쓰기 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role writeAccessRole = Role.ALL;

    // 답글 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role replyAccessRole = Role.ALL;

    // 댓글 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role commentAccessRole = Role.ALL;

    // 에디터 사용 여부
    private boolean useEditor;

    // 파일 첨부 사용여부
    private boolean useAttachFile;

    // 이미지 첨부 사용여부
    private boolean useAttachImage;

    // 글 작성 후 이동
    @Column(length=10, nullable = false)
    private String locationAfterWriting = "view";

    // 답글 사용 여부
    private boolean useReply;

    // 댓글 사용 여부
    private boolean useComment;

    // 게시판 스킨
    @Column(length = 20, nullable = false)
    private String skin = "default";
}
