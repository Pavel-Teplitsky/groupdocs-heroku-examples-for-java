package com.groupdocs.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends AbstractController {

    @RequestMapping("/")
    public String root() {
        return index();
    }

    @RequestMapping("/index.html")
    public String index() {

        log.info("/index.htm ");
        return "home/index";
    }
}
