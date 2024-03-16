package com.scy.resoult;

public class Resoult {
    // 状态码
    private int statusCode;

    // 返回数据
    private Object data;

    // Token
    private String token;

    // 构造方法，用于初始化 Resoult 类的实例
    public Resoult(int statusCode, Object data, String token) {
        this.statusCode = statusCode;
        this.data = data;
        this.token = token;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
