package com.practice.homework.module.user.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class UserUpdateDto {

    @Data
    public static class UserUpdateRequest implements Serializable {
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
        private String userType;
        private String userSubtype;
        private Date loginLatestDate;
        private Date logoutLatestDate;
        private String username;
    }

}
