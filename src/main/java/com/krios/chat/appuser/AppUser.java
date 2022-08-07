package com.krios.chat.appuser;

import com.krios.chat.appuser.role.Role;
import com.krios.chat.appuser.role.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AppUser implements UserDetails {
    @SequenceGenerator(name = "appUser_sequence", sequenceName = "appUser_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUser_sequence")
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Cascade({SAVE_UPDATE, MERGE, PERSIST})
    private List<Role> role;

    public AppUser(String username, String email, String password, String firstName, String lastName, List<Role> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authority = role.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).toList();
        return authority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
