package com.practice.homework.entity.Base;

import com.practice.homework.commons.Base;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the user database table.
 */
@Data
@Table("topic")
public class TopicBase extends Base {
    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.linearId;
    }

    @Id
    @Column("linear_id")
    private String linearId;

    @Column("topic_subject")
    private String topicSubject;
    @Column("topic_detail")
    private String topicDetail;
    @Column("visitor_amount")
    private BigDecimal visitorAmount;
    @Column("last_visitor_date")
    private Date lastVisitorDate;
    @Column("last_visitor_by")
    private String lastVisitorBy;

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
