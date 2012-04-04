package com.nexse.curator.util;

/**
 * @author : germano giudici
 */
public class AjaxResponse{
    private String success = "true";
    private String error;

    public AjaxResponse(String error) {
        this.error = error;
    }

    public AjaxResponse() {
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
