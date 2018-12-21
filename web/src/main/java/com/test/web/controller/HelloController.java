package com.test.web.controller;

import com.test.common.util.I18nUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ezbuy on ${date}
 */
@RestController
@Slf4j
@CrossOrigin
public class HelloController {


    @GetMapping("/hello")
    public String hello(){

        String res = I18nUtils.getDefaultMessage("message.LOGIN_OK");
        log.info(res);
       return res;
    }


    @PostMapping("/hello")
    public String hello(@RequestBody String name){
        String res = I18nUtils.getDefaultMessage("message.LOGIN_OK");
        log.info(res);
        return res;
    }

}
