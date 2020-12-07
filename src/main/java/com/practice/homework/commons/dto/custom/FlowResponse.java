package com.practice.homework.commons.dto.custom;

public class FlowResponse {
    private boolean status;
    private Object data;

    public FlowResponse() {
    }

    public FlowResponse(boolean status) {
        this.status = status;
    }

    public FlowResponse(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

//    public Object getData() {
//        if (data instanceof SignedTransaction) {
//            return null;//((SignedTransaction) data).getId().toString();
//        }
//        return data;
//    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
