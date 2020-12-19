package com.practice.homework.entity;

import com.practice.homework.entity.Base.MemberBase;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("member")
public class Member extends MemberBase {

}
