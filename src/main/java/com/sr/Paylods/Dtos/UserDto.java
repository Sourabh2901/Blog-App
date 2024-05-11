package com.sr.Paylods.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 2 ,message = "Username must be minimum 2 Character")
    private String username;

    @NotNull
    @Size(min = 4 ,message = "password should have atleast 4 character")
    private String password;
}
