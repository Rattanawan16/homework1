package com.practice.homework.module.member.core.dto;

import lombok.Data;

import java.io.Serializable;

public class MemberDeleteDto {
    @Data
    public static class MemberDeleteDtoRequest implements Serializable {
        private String id;
        private String username;
    }
}
