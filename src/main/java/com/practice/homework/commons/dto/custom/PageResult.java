package com.practice.homework.commons.dto.custom;

import lombok.Data;

@Data
public class PageResult {
    //The display page number
    private Integer pageSize = 0;
    //The total number of elements
    private Long total = 0L;
    //The total number of pages
    private Integer totalPages = 0;
    //The current page number
    private Integer pageNumber = 0;
}
