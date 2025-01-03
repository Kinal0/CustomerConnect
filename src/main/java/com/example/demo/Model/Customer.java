package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerid;

    private String lname;

    private String fname;

    private String city;

    private String state;

    private int zipcode;

    private Date registrationdate;

    private Date last_updated;




}
