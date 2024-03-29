package sejongZoo.sejongZoo.user.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import sejongZoo.sejongZoo.common.emun.Role;
import java.util.ArrayList;
import java.util.Collection;


@Entity(name="user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class User implements UserDetails {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(columnDefinition = "varchar(20)")
    private String studentId;
    @NotNull
    @Column(columnDefinition = "varchar(10)")
    private String name;
    @NotNull
    @Column(columnDefinition = "varchar(30)")
    private String password;
    @Column(columnDefinition = "varchar(20)")
    private String nickname;
    private String refreshToken;

//    //학과 추가
//    private String major;
    @NotNull
    @ColumnDefault("50")
    private Integer manner;
    @Column(columnDefinition = "varchar(50)")
    private String role;



    @Builder
    public User( String name, String studentId, String password, String nickname, Integer manner) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.nickname = nickname;
        this.manner = manner;
    }

    public void setRole(Role role){
        if(this.role == null) {
            this.role = role.getValue();
        }
        else{
            this.role += ",";
            this.role += role.getValue();
        }
    }
    public void setRefreshToken(String refreshToken){
        this.refreshToken =refreshToken;
    }
    public void updateRefreshToken(String refreshToken){
        this.refreshToken=refreshToken;
    }
    public User hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

    public Boolean checkPassword(String rawPassword, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(rawPassword, this.password);
    }
    public void updatePassword(String password){
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : this.role.split(",")) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return studentId;
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
