package com.practice.homework.entity;

import com.practice.homework.entity.Base.UserBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user")
public class User extends UserBase {

}
