package com.practice.homework.module.member.core.dto;

import com.practice.homework.commons.dto.custom.PageLimit;
import com.practice.homework.commons.dto.custom.PageResult;
import com.practice.homework.dto.entity.MemberEntityDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MemberListDto {

    @Data
    public static class MemberListRequest implements Serializable {
        private String id;

        private String idCardNo;
        private String loginName;
        private String firstNameTh;
        private String lastNameTh;
        private String firstNameEn;
        private String lastNameEn;

        private String email;
        private String title;
        private Date birthDateFrom;
        private Date birthDateTo;
        private String gender;
        private String marriageStatus;
        private String nationality;
        private String mobile;
        private String telephone;
        private String occupation;
        private String company;
        private String memberType;
        private String memberSubtype;
        private Date loginLatestDateFrom;
        private Date loginLatestDateTo;
        private Date logoutLatestDateFrom;
        private Date logoutLatestDateTo;

        private String status;
        private String state;
        private String createBy;
        private Date createDateFrom;
        private Date createDateTo;
        private String changeBy;
        private Date changeDateFrom;
        private Date changeDateTo;

        private PageLimit limit;

        private String sortBy;
        private String sortDirectionBy;
    }

    @Data
    public static class MemberListEntityResponse {
        private List<MemberEntityDto> data;
        private PageResult page;
    }
}
