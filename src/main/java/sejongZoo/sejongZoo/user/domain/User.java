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
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="student_id", columnDefinition = "varchar",length = 20,nullable = false)
    private String studentId;

    @Column(name="name", columnDefinition = "varchar",length = 10, nullable = false)
    private String name;


    @Column(name="major", columnDefinition = "varchar", length = 20, nullable = false)
    private String major;


    @Column(name="password", columnDefinition = "varchar", nullable = false)
    private String password;

    @Column(name="nickname", columnDefinition = "varchar",length = 20, nullable = false)
    private String nickname;

    @Column(name="refresh_token")
    private String refreshToken;

    @ColumnDefault("50")
    private Integer manner;

    @Column(name="role", columnDefinition = "varchar", length = 50)
    private String role;

    @Column(name="deleted")
    @ColumnDefault("false")
    private Boolean deleted;

    @Builder
    public User( String name, String major, String studentId, String password, String nickname, Integer manner) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.nickname = nickname;
        this.manner = manner;
		this.major=major;
    }
    public void updateInfo(String major, String nickname){
        this.major = major;
        this.nickname = nickname;
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
    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
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
