package com.practice.homework.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SQLParameter {
    private String key;
    private Object value;

    public static SQLParameter of(String key, Object value) {
        return new SQLParameter(key, value);
    }
}
