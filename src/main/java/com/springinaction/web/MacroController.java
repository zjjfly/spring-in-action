package com.springinaction.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zjjfly on 2017/3/6.
 */
@Controller
public class MacroController {

    @GetMapping("/marco")
    public String macro(){
        return "marco";
    }

    @GetMapping("/marcopolo")
    public String marcopolo(){
        return "marcopolo";
    }
}
