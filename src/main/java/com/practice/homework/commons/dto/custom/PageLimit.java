package com.practice.homework.commons.dto.custom;

public class PageLimit {
    public static final PageLimit DEFAULT = new PageLimit(1, 200);
    public static final PageLimit ONE = new PageLimit(1, 1);

    private Integer pageNumber = 1;
    private Integer pageSize = 200;

    public PageLimit() {
    }

    public PageLimit(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
