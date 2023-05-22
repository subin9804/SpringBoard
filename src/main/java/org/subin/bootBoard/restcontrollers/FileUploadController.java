package org.subin.bootBoard.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 import org.subin.bootBoard.commons.CommonException;

@RestController
public class FileUploadController {

    @GetMapping("/file/upload")
    public void upload() {

    }
}
