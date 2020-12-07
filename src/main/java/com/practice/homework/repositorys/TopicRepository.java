package com.practice.homework.repositorys;

import com.practice.homework.entity.Topic;
import com.practice.homework.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TopicRepository extends CrudRepository<Topic, String>, TopicCustomRepository {
    @Query("SELECT * FROM topic WHERE linear_id = :id AND status = :status")
    Topic findByIdAndStatus(@Param("id") String id, @Param("status") String status);

    default Topic findByIdString(@Param("id") String id) {
        return findByIdAndStatus(id, "A");
    }

    @Query("SELECT visitor_amount FROM topic WHERE linear_id = :id AND status = :status")
    BigDecimal getVisitorAmountByIdAndStatus(@Param("id") String id, @Param("status") String status);

    default BigDecimal getVisitorAmountByIdString(@Param("id") String id) {
        return getVisitorAmountByIdAndStatus(id, "A");
    }
}

