package spring.dev.quanlithuctap.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends Base{
    private String name;
    private String description;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;
}
