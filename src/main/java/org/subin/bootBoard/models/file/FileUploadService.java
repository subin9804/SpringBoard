package org.subin.bootBoard.models.file;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.subin.bootBoard.entities.FileInfo;
import org.subin.bootBoard.repositories.FileInfoRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileInfoService infoService;
    private final FileInfoRepository repository;

    public List<FileInfo> upload(MultipartFile[]files, String gid, String location) {
        gid = gid == null || gid.isBlank() ? UUID.randomUUID().toString() : gid;
        List<FileInfo> uploadedFiles = new ArrayList<>();

        for(MultipartFile file : files) {
            String fileType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            /** 파일 정보 저장 처리 */
            FileInfo item = FileInfo.builder()
                    .fileName(fileName)
                    .fileType(fileType)
                    .extension(extension)
                    .gid(gid)
                    .location(location)
                    .build();
            repository.saveAndFlush(item);

            infoService.addFileInfo(item); // 파일 경로, 파일 URL 등의 추가 정보


            // 파일 업로드 처리
            try {
                File _file = new File(item.getFilePath());
                file.transferTo(_file);

                if(fileType.indexOf("image") != -1) {   // 이미지 형식 파일
                    int width = 150;
                    int height = 150;
                    File _thumb = new File(infoService.getThumbPath(item.getId(), item.getExtension(), width, height));
                    Thumbnails.of(_file)
                            .size(150, 150)
                            .toFile();
                }
                uploadedFiles.add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return uploadedFiles;
    }

    public List<FileInfo> upload(MultipartFile[] files, String gid) {
        return upload(files, gid, null);
    }

    public List<FileInfo> upload(MultipartFile[] files) {
        return upload(files, null);
    }
}
