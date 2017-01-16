package com.jingan.easydearbusiness.entity;

/**
 * @author CBOK
 * @date 2016/11/7 21:45
 * @description:
 */

public class ResponseModel<T> {

    private int Code;

    private String Message;

    private T Data;

    public ResponseModel(int Code, String message, T data) {
        this.Code = Code;
        this.Message = message;
        this.Data = data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }
}
