
package com.example.finale.dto;

import com.example.finale.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {



    private Long id;

    private String name;
    private String email;
    private String password;

    private UserRole userRole;



}
