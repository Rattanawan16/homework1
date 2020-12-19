package com.practice.homework.dto.entity;

import com.practice.homework.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntityDto {

    private Member state;
    private MemberEntityInfoDto info;

    public MemberEntityDto(Member state) {
        this.state = state;
    }

    @Data
    @AllArgsConstructor
    public static class MemberEntityInfoDto {

    }
}
