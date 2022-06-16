package com.daon.host.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController implements ErrorController {


    private final String ERROR_PATH = "/error2";

    @GetMapping(ERROR_PATH)
    public String redirectRoot(){
        return "index.html";
    }

    public String getErrorPath(){
        return null;
    }
}
