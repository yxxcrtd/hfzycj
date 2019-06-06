package com.zycj.sdk.common.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * Created by yi.wang1 on 2015/6/1.
 */
public class LocalRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {
    public LocalRequestMappingHandlerAdapter(){
        setWebBindingInitializer(new LocalDateBinding());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        setMessageConverters(messageConverters);
    }
}
