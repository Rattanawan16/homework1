package com.practice.homework.dto.entity;

import com.practice.homework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDto {

    private User state;
    private UserEntityInfoDto info;

    public UserEntityDto(User state) {
        this.state = state;
    }

    @Data
    @AllArgsConstructor
    public static class UserEntityInfoDto {

    }
}
