package com.wl.ko.home.controller;


import com.wl.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/alipay")
@Controller
public class AlipayController {

    /** Log */
    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayController.class);
	
	@Autowired
	private IBaseService baseService;

}
