package com.empower.ecom.dto;

public class jwtResponce {

    private String token;

    public jwtResponce(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
