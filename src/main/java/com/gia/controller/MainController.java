package com.gia.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Fenglin on 2017/6/30.
 */
@Controller
public class MainController {

    @RequestMapping(value = {"", "/homePage"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public String homePage() {
        return "homePage";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminPage() {
        return "adminPage";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public String userPage() {
        return "userPage";
    }

    @RequestMapping(value = {"/loginPage"}, method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String forbiddenPage() {
        return "403";
    }
}
