package com.example.demo.response;

public class Response {
    private ReturnedResponses returnedResponses;
    private Data data;

    public Response(int errorCode, String description) {
        this.returnedResponses = new ReturnedResponses(errorCode, description);
    }

    public Response(int errorCode, String description, Object object) {
        this.returnedResponses = new ReturnedResponses(errorCode, description);
        this.data = new Data(object);
    }

    public ReturnedResponses getReturnedResponses() {
        return returnedResponses;
    }

    public void setReturnedResponses(ReturnedResponses returnedResponses) {
        this.returnedResponses = returnedResponses;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
