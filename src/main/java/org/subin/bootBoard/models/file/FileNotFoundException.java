package org.subin.bootBoard.models.file;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException() {
        super(bundleError.getString("File.notExists"),HttpStatus.BAD_REQUEST);
    }
}
