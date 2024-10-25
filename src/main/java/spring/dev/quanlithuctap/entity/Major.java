package spring.dev.quanlithuctap.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Major extends Base{
    private String name;
    private String description;

    @OneToMany(mappedBy = "major", cascade = CascadeType.MERGE)
    public List<User> users;
}
