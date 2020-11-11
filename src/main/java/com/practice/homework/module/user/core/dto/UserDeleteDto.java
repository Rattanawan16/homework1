package com.practice.homework.module.user.core.dto;

import lombok.Data;

import java.io.Serializable;

public class UserDeleteDto {
    @Data
    public static class UserDeleteDtoRequest implements Serializable {
        private String id;
        private String username;
    }
}
