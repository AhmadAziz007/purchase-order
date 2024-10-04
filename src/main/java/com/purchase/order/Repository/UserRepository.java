package com.purchase.order.Repository;

import com.purchase.order.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from mst_user where user_id = :userId", nativeQuery = true)
    Optional<User> findByUserId(@Param("userId") Long userId);

    @Query(value = "select a.* from mst_user a order by a.user_id asc", nativeQuery = true)
    List<User> findAll();
}
