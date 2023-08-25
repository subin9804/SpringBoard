package org.subin.bootBoard.models.file;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.entities.FileInfo;

import java.io.*;

@Service
@RequiredArgsConstructor
public class FileDownloadService {

    private final HttpServletResponse response;
    private final FileInfoService infoService;

    public void download(Long id) {
        FileInfo item = infoService.get(id);
        String filePath = item.getFilePath();
        File file = new File(filePath);

        if(!file.exists()) {    // 파일이 없는 경우
            throw new FileNotFoundException();
        }

        String fileName = item.getFileName();

        try(FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream out = response.getOutputStream();

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Cache-Control", "must-revalidate"); // 계속해서 캐시 갱신 가능
            response.setHeader("Pragma", "public"); // Cache-Control과 동일한 기능
            response.setIntHeader("Expires", 0);   // 브라우져가 만료되지 않도록 설정
            response.setHeader("Content-Length", "" + file.length());

            while(bis.available() > 0) {
                out.write(bis.read());
            }

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }




//        try {
//            PrintWriter out = response.getWriter();
//            out.println("test1");
//        } catch (IOException) {
//            throw new RuntimeException();
//        }
    }

}
