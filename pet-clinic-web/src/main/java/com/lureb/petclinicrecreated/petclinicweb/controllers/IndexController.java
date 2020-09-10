package com.lureb.petclinicrecreated.petclinicweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "vetList.html"})
    public String index() {
        return "index";
    }


    @GetMapping({"/oups"})
    public String oups() {
        return "notImplemented";
    }

}
