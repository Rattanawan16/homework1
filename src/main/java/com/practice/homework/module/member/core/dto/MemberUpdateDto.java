package com.practice.homework.module.member.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class MemberUpdateDto {

    @Data
    public static class MemberUpdateRequest implements Serializable {
        private String id;
        private String idCardNo;
        private String loginName;
        private String loginPassword;
        private String firstNameTh;
        private String lastNameTh;
        private String firstNameEn;
        private String lastNameEn;

        private String email;
        private String title;
        private Date birthDate;
        private String gender;
        private String marriageStatus;
        private String nationality;
        private String mobile;
        private String telephone;
        private String occupation;
        private String company;
        private String memberType;
        private String memberSubtype;
        private Date loginLatestDate;
        private Date logoutLatestDate;
        private String username;
    }

    @Data
    public static class MemberVisitTopicRequest implements Serializable {
        private String memberId;
        private String topicId;
        private String username;
    }

    @Data
    public static class RandomMemberVisitRandomTopicRequest implements Serializable {
        private int amount;
        private String username;
    }

}
