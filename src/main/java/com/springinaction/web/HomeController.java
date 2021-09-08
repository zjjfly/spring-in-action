package com.springinaction.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by zjjfly on 2016/12/27.
 */
@Controller
@RequestMapping(value = {"/","/index","/index.html"})
public class HomeController {
    @RequestMapping
    public String home(){
        return "home";
    }

    @RequestMapping("/home")
    public String goHome(){
        return "home";
    }
}
