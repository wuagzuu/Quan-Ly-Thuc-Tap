package spring.dev.quanlithuctap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.dev.quanlithuctap.entity.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {

}
