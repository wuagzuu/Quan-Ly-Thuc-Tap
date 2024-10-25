package spring.dev.quanlithuctap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.dev.quanlithuctap.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String name);
}
