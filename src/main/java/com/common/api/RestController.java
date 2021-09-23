package com.common.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/root")
public class RestController {

    @RequestMapping(method = RequestMethod.GET, value = "/leon")
    public String get() {
        return "hello";
    }
}
