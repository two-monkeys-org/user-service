package org.monke.userservice.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.monke.userservice.utils.validators.annotations.Email;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    private String password;
}
