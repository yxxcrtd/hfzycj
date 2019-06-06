package com.hfzycj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * System Log AOP
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {

    /**
     * 模块名称
     */
    String model() default "";

    /**
     * 日志描述
     */
    String description() default "";

}
