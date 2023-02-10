package com.system.futsal_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "shb_user_seq_gen", sequenceName = "shb_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Column()
    private String email;


    @Column(name = "mobile_no")
    private String mobileNo;


    @Column(name = "Name")
    private String Name;


    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
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
