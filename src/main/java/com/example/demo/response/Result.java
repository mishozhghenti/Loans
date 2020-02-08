package com.example.demo.response;

public enum Result {
    OK(0, "OK"),
    ERROR(-1, "Common Error");
    private int errorCode;
    private String description;

    Result(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;

    }

    public Response getResponse(Object object) {
        return new Response(this.getErrorCode(), this.getDescription(), object);
    }

    public Response getResponse() {
        return new Response(this.getErrorCode(), this.getDescription());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
