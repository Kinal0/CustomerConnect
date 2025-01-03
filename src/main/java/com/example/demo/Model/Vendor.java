package com.example.demo.Model;

import jakarta.persistence.*;

import lombok.Data;

import java.sql.Date;


@Data

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vendorid;

    private String name;

    private String contact;

    private String email;

    private String address;

    private String phone;

    private Date lastupdate;



}
