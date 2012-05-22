package com.nexse.swat.web.services;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 21/05/12
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class AjaxResponse<T> {
    private String success;
    private T data;

    public AjaxResponse() {
    }

    public AjaxResponse(String success, T data) {
        this.success = success;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
