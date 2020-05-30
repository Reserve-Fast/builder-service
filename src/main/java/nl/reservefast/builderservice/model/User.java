package nl.reservefast.builderservice.model;

import lombok.Getter;
import lombok.Setter;
import nl.reservefast.builderservice.model.enums.Role;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Role role = Role.USER;
}
