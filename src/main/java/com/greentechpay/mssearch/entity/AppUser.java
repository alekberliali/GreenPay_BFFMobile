package com.greentechpay.mssearch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile", schema = "public")
public class AppUser {
    @Id
    private String id;
    @Email
    private String email;
    private String fin;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "user_name")
    private String userName;
}
