package com.wl.base.model;

public class ReturnMessage {
    //状态信息,200表示成功，500表示服务器端错误
    private int status;
    //提示信息
    private String msg;
    //对象信息
    private Object value;

    public ReturnMessage(int status, String msg, Object value) {
        super();
        this.status = status;
        this.msg = msg;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}