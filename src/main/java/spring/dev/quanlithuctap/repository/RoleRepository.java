package spring.dev.quanlithuctap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.dev.quanlithuctap.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String user);
}
