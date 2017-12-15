package com.glsid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by X-MART on 5/11/2017.
 */
@Controller
public class SecurityController {
    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }
    @RequestMapping(value = "/")
    public String home(){
        return "redirect:/societe";
    }
    @RequestMapping(value = "/403")
    public String accessDenied(){
        return "/403";
    }
}
