package org.subin.bootBoard.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.subin.bootBoard.commons.CommonException;

@ControllerAdvice("org.subin.bootBoard.controllers")
public class CommonController {

    public String errorHandler(Exception e, Model model, HttpServletResponse response) {

        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if(e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus().value();
        }

        response.setStatus(status);

        model.addAttribute("status", status);
        model.addAttribute("message", e.getMessage());
        model.addAttribute("exception", e);

        return "error/common";
    }
}
