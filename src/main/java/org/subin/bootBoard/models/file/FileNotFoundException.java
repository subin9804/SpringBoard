package org.subin.bootBoard.models.file;

import org.springframework.http.HttpStatus;
import org.subin.bootBoard.commons.CommonException;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException() {
        super(bundleValidation.getString("File.notExists"),HttpStatus.BAD_REQUEST);
    }
}
