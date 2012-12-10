package com.groupdocs.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SamplesController extends AbstractController {

    @RequestMapping("/sample1")
    public String sample1() {
        log.info("/sample1.htm ");
        return "home/sample1";
    }

    @RequestMapping("/sample2")
    public String sample2() {
        log.info("/sample2.htm ");
        return "home/sample2";
    }

    @RequestMapping("/sample3")
    public String sample3() {
        log.info("/sample3.htm ");
        return "home/sample3";
    }

    @RequestMapping("/sample4")
    public String sample4() {
        log.info("/sample4.htm ");
        return "home/sample4";
    }

    @RequestMapping("/sample5")
    public String sample5() {
        log.info("/sample5.htm ");
        return "home/sample5";
    }

    @RequestMapping("/sample6")
    public String sample6() {
        log.info("/sample6.htm ");
        return "home/sample6";
    }

    @RequestMapping("/sample7")
    public String sample7() {
        log.info("/sample7.htm ");
        return "home/sample7";
    }

    @RequestMapping("/sample8")
    public String sample8() {
        log.info("/sample8.htm ");
        return "home/sample8";
    }

    @RequestMapping("/sample9")
    public String sample9() {
        log.info("/sample9.htm ");
        return "home/sample9";
    }

    @RequestMapping("/sample10")
    public String sample10() {
        log.info("/sample10.htm ");
        return "home/sample10";
    }
}
