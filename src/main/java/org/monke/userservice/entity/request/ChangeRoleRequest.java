package org.monke.userservice.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.monke.userservice.utils.validators.annotations.Email;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChangeRoleRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String role;
}
