package com.hfzycj.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("site")
public class SiteController extends BaseController {

    @GetMapping("news/view")
    String view(@RequestParam(value = "id") long id) {
        return newsService.increase(id);
    }

}
