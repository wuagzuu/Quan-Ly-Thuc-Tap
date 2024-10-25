package spring.dev.quanlithuctap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends Base{
    private String name;
    private String phone;
    private Date dob;
    private String address;
    private String email;
    private String username;
    private String password;
    private String sex;
    private Boolean firstLogin;
    private Boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Major major;
}
