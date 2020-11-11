package com.practice.homework.commons.dto.custom;

public class QueryResponse<T> {
    private T data;
    private Long total;

    public QueryResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
