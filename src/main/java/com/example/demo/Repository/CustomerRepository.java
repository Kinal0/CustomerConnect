package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query(value = "SELECT * FROM user WHERE username = :name", nativeQuery = true)
    User findUserByName(@Param("name") String name);


}
