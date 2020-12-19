package com.practice.homework.entity.Base;

import com.practice.homework.commons.Base;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;


/**
 * The persistent class for the member database table.
 */
@Data
@Table("member")
public class MemberBase extends Base {
    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.linearId;
    }

    @Id
    @Column("linear_id")
    private String linearId;

    @Column("id_card_no")
    private String idCardNo;
    @Column("login_name")
    private String loginName;
    @Column("login_password")
    private String loginPassword;
    @Column("first_name_th")
    private String firstNameTh;
    @Column("last_name_th")
    private String lastNameTh;
    @Column("first_name_en")
    private String firstNameEn;
    @Column("last_name_en")
    private String lastNameEn;

    @Column("email")
    private String email;
    @Column("title")
    private String title;
    @Column("birth_date")
    private Date birthDate;
    @Column("gender")
    private String gender;
    @Column("marriage_status")
    private String marriageStatus;
    @Column("nationality")
    private String nationality;
    @Column("mobile")
    private String mobile;
    @Column("telephone")
    private String telephone;
    @Column("occupation")
    private String occupation;
    @Column("company")
    private String company;
    @Column("member_type")
    private String memberType;
    @Column("member_subtype")
    private String memberSubtype;
    @Column("login_latest_date")
    private Date loginLatestDate;
    @Column("logout_latest_date")
    private Date logoutLatestDate;

    @Column("state")
    private String state;
    @Column("status")
    private String status;
    @Column("create_by")
    private String createBy;
    @Column("create_date")
    private Date createDate;
    @Column("change_by")
    private String changeBy;
    @Column("change_date")
    private Date changeDate;
}
