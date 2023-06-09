package org.subin.bootBoard.models.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.commons.AuthorizationException;
import org.subin.bootBoard.commons.MemberUtil;
import org.subin.bootBoard.entities.FileInfo;
import org.subin.bootBoard.repositories.FileInfoRepository;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
    private final FileInfoService infoService;
    private final MemberUtil memberUtil;
    private final FileInfoRepository repository;

    /**
     * 파일 등록번호로 파일 정보, 업로드 파일 삭제
     * @param id: 파일 등록 번호
     */
    public void delete(Long id) {
        FileInfo fileInfo = infoService.get(id);

        /** 관리자 및 파일을 등록한 사용자만 삭제 가능 여부 체크 */
        String createdBy = fileInfo.getCreateBy();

        // 비회원이거나 게시글을 작성한 사람이 아닐 때
        if(fileInfo.isDone() && createdBy != null && !memberUtil.isAdmin() && (!memberUtil.isLogin() || !memberUtil.getMember().getUserId().equals(createdBy))) {
            throw new AuthorizationException("File.notYours");
        }

        /**
         * 1. 정보 삭제
         * 2. 실제 파일 삭제
         */
        String filePath = fileInfo.getFilePath();
        repository.delete(fileInfo);
        repository.flush();

        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
    }

    /**
     * Gid로 파일 정보, 업로드된 파일 삭제
     * @param gid
     */
    public void delete(String gid) {
        List<FileInfo> files = infoService.gets(gid);

        files.stream().forEach(f -> delete(f.getId()));
    }

}
