package org.monke.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.monke.userservice.utils.validators.annotations.Email;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private String roles;
    private String password;
    private boolean isFirstLogin;
}
