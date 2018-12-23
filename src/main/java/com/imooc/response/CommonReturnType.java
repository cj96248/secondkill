package com.imooc.response;

public class CommonReturnType {
    /**
     * Success or Fail
     */
    private String status;
    /**
     * status=success - > return data
     * status=fail - > return error code
     */
    private Object data;

    public CommonReturnType(Object data){
        this.status = "success";
        this.data = data;
    }

    public CommonReturnType(String status, Object data){
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
