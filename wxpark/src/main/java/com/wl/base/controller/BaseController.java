package com.wl.base.controller;

import com.wl.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/base")
@Controller
public class BaseController {

    /** Log */
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IBaseService baseService;

}
