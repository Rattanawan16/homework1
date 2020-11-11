package com.practice.homework.commons.dto.custom;

import lombok.Data;

@Data
public class PageQuery {
    private Integer offset = 0;
    private Integer pageSize = 0;
}
