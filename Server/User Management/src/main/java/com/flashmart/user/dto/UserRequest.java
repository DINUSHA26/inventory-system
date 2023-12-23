package com.flashmart.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private int type;
    private String fname;
    private String lname;
    private String mobile;
    private String email;
    private String password;

}
