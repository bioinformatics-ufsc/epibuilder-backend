package ufsc.br.epibuilder.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a user in the system.
 * Implements {@link UserDetails} for authentication and authorization purposes.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    /** The unique identifier of the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The full name of the user. */
    private String name;

    /** The username used for authentication. */
    private String username;

    /** The hashed password of the user. */
    private String password;

    /** The role assigned to the user. */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Returns the password used for authentication.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used for authentication.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account is expired.
     *
     * @return {@code true} if the account is not expired, {@code false} otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return {@code true} if the account is not locked, {@code false} otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are expired.
     *
     * @return {@code true} if the credentials are not expired, {@code false} otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return {@code true} if the user is enabled, {@code false} otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
