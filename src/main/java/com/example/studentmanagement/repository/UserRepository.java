package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo>
     findByUsername(String username);
   List<UserInfo> findByUsernameNot(String username);

    Optional<UserInfo> findByEmail(String email);

    @Query(value = "select * from user_info where username like %:keyword% or firstname like  %:keyword%", nativeQuery = true)
    List<UserInfo> findByKeyword(@Param("keyword") String keyword);



}
