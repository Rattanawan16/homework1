package com.practice.homework.repositorys;

import com.practice.homework.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>, UserCustomRepository {
    @Query("SELECT * FROM public.user WHERE linear_id = :id AND status = :status")
    User findByIdAndStatus(@Param("id") String id, @Param("status") String status);

    default User findByIdString(@Param("id") String id) {
        return findByIdAndStatus(id, "A");
    }

}

