package com.zbf.user.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LCG
 * 作者: LCG
 * 日期: 2020/9/7  23:54
 * 描述:
 */
@RestController
public class UserController {

    @RequestMapping("test01")
    public String test01(){
         return "ok";
    }

}
