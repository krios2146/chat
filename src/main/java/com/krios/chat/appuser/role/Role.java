package com.krios.chat.appuser.role;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role {
    @SequenceGenerator(name = "app_user_role_sequence", sequenceName = "app_user_role_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_role_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }
}
