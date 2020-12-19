package com.practice.homework.repositorys;

import com.google.common.base.CaseFormat;
import com.practice.homework.commons.dto.custom.PageQuery;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.commons.utils.DataUtils;
import com.practice.homework.commons.utils.SQLCriteriaBuilder;
import com.practice.homework.commons.utils.SQLParameter;
import com.practice.homework.entity.Topic;
import com.practice.homework.module.topic.core.dto.TopicListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TopicCustomRepositoryImpl implements TopicCustomRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    @Override
    public QueryResponse<List<Topic>> findByCriteria(TopicListDto.TopicListRequest request) throws Exception {
        QueryResponse<List<Topic>> queryResponse = new QueryResponse<>();
        String dbTableName = "topic";
        SQLCriteriaBuilder cb = new SQLCriteriaBuilder();

        if ((request.getId() != null)) {
            cb.addQuery("AND linear_id = :linearId", SQLParameter.of("linearId", request.getId()));
        }

        if ((request.getTopicSubject() != null)) {
            cb.addQuery("AND topic_subject like :topicSubject", SQLParameter.of("topicSubject", '%' + request.getTopicSubject() + '%'));
        }
        if ((request.getTopicDetail() != null)) {
            cb.addQuery("AND login_name like :topicDetail", SQLParameter.of("topicDetail", '%' + request.getTopicDetail() + '%'));
        }
        if ((request.getVisitorAmount() != null)) {
            cb.addQuery("AND first_name_th = :visitorAmount", SQLParameter.of("visitorAmount", request.getVisitorAmount()));
        }
        if ((request.getLastVisitorBy() != null)) {
            cb.addQuery("AND last_visitor_by like : lastVisitorBy", SQLParameter.of("lastVisitorBy", '%' + request.getLastVisitorBy() + '%'));
        }
        if ((request.getLastVisitorDateFrom() != null && request.getLastVisitorDateTo() != null)) {
            cb.addQuery(" AND last_visitor_date BETWEEN :lastVisitorDateFrom AND :lastVisitorDateTo ",
                    SQLParameter.of("lastVisitorDateFrom", request.getLastVisitorDateFrom()),
                    SQLParameter.of("lastVisitorDateTo", request.getLastVisitorDateTo())
            );
        }

        if ((request.getState() != null)) {
            cb.addQuery("AND state = :state", SQLParameter.of("state", request.getState()));
        }
        if ((request.getStatus() != null)) {
            cb.addQuery("AND status = :status", SQLParameter.of("status", request.getStatus()));
        }
        if ((request.getCreateBy() != null)) {
            cb.addQuery("AND create_by = :createBy", SQLParameter.of("createBy", request.getCreateBy()));
        }
        if ((request.getChangeBy() != null)) {
            cb.addQuery("AND change_by = :changeBy", SQLParameter.of("changeBy", request.getChangeBy()));
        }
        if ((request.getChangeDateFrom() != null && request.getChangeDateTo() != null)) {
            cb.addQuery(" AND change_date BETWEEN :changeDateFrom AND :changeDateTo ",
                    SQLParameter.of("changeDateFrom", request.getChangeDateFrom()),
                    SQLParameter.of("changeDateTo", request.getChangeDateTo())
            );
        }
        if ((request.getCreateDateFrom()!= null && request.getCreateDateTo() != null)) {
            cb.addQuery("AND create_date BETWEEN :createDateFrom AND :createDateTo ",
                    SQLParameter.of("createDateFrom", request.getCreateDateFrom()),
                    SQLParameter.of("createDateTo", request.getCreateDateTo())
            );
        }

        SQLCriteriaBuilder countQuery = new SQLCriteriaBuilder();
        countQuery.addQuery("SELECT count(*) FROM "+dbTableName+" WHERE 1 = 1");
        countQuery.addQuery(cb.getSql().toString());
        countQuery.setParameters(cb.getParameters());

        Long total = jdbcTemplate.queryForObject(countQuery.getSql().toString(), countQuery.getParameters(), Long.class);
        if (total > 0) {
            PageQuery pageQuery = DataUtils.getPageQuery(request.getLimit());

            SQLCriteriaBuilder mainQuery = new SQLCriteriaBuilder();
            mainQuery.addQuery("SELECT * FROM "+dbTableName+" WHERE 1 = 1");
            mainQuery.addQuery(cb.getSql().toString());
            mainQuery.setParameters(cb.getParameters());

            if ((request.getSortBy()!= null && request.getSortDirectionBy() != null)) {
                mainQuery.addQuery("ORDER BY " + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, request.getSortBy()) + " " + request.getSortDirectionBy());
            } else {
//                mainQuery.addQuery("ORDER BY change_date DESC");
            }

            mainQuery.addQuery("LIMIT :pageSize OFFSET :offset ",
                    SQLParameter.of("pageSize", pageQuery.getPageSize()),
                    SQLParameter.of("offset", pageQuery.getOffset()));

            List<Topic> queryResults = jdbcTemplate.query(mainQuery.getSql().toString(), mainQuery.getParameters(), new BeanPropertyRowMapper<>(Topic.class));
            queryResponse.setData(queryResults);
        }

        queryResponse.setTotal(total);
        return queryResponse;
    }
}
