package com.flashmart.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userid;
    private int type;
    private String fname;
    private String lname;

    @Column(length = 15)
    private String mobile;
    private String email;
    private String password;
}
