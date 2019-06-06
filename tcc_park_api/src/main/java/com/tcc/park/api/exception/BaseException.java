/*
 * BaseException.java 
 */
package com.tcc.park.api.exception;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * 类 <code>BaseException</code> 异常处理类
 *
 */

public class BaseException extends RuntimeException implements Serializable{
   
	private static final long serialVersionUID = 3530886112166554666L;
	
	private Logger logger =Logger.getLogger(getClass());
    protected Throwable rootCause =null;
    
    public BaseException() {
       super();
    }

    /**
     * @param s
     */
    public BaseException(String s) {
       this(s, null);
       rootCause = this;
    }

    public BaseException(String s, Throwable e) {
       super(s, e);
       if(e instanceof BaseException) {
          rootCause = ((BaseException)e).rootCause;
       } else {
          rootCause = e;
       }
       logger.debug(s, e);
    }

    public BaseException(Throwable e) {
       this("", e);
    }

    public void setRootCause(Throwable e){
    	rootCause=e;
    }
    /**
     * @return
     */
    public Throwable getRootCause() {
       return rootCause;
    }
 }
