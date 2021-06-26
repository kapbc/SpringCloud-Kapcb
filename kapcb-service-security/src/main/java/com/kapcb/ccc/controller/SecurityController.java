package com.kapcb.ccc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <a>Title: SecurityController </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/26 15:34
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @GetMapping("hello")
    public String hello() {
        return "Hello Spring Security";
    }
}
