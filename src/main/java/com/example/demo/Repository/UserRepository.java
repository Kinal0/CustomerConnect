package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query(value = "SELECT * FROM user WHERE username = :name", nativeQuery = true)
    User findUserByName(@Param("name") String name);



}
