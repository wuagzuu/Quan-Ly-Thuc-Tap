package spring.dev.quanlithuctap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(updatable = false)
    protected Date createdDate;
    protected Date updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date(System.currentTimeMillis());
        updatedDate = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date(System.currentTimeMillis());
    }
}
