package org.subin.bootBoard.controllers.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.subin.bootBoard.commons.rests.JSONData;
import org.subin.bootBoard.entities.FileInfo;
import org.subin.bootBoard.models.file.FileUploadService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test/upload")
public class TestController {

    private final FileUploadService uploadService;

    @GetMapping
    public String upload() {

        return "test";
    }


    @PostMapping
    @ResponseBody
    public void uploadPs(MultipartFile[] files,String gid, String location)  {

    }
}
