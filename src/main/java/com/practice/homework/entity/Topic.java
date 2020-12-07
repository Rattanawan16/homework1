package com.practice.homework.entity;

import com.practice.homework.entity.Base.TopicBase;
import com.practice.homework.entity.Base.UserBase;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("topic")
public class Topic extends TopicBase {

}
