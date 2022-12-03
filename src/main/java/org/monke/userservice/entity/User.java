package org.monke.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.monke.userservice.utils.validators.annotations.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Email
    @Id
    private String email;
    private String name;
    private String surname;
    private String roles;
    private String password;
    private boolean isFirstLogin;
}
