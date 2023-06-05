package com.srinu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "post")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Where(clause = "is_deleted = false")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reference")
    Post referencePost;

    String createdBy;

    String updatedBy;

    LocalDateTime createdTs;

    LocalDateTime updatedTs;

    Boolean isDeleted = false;

    @PrePersist
    public void perPersist() {
        createdTs = LocalDateTime.now();
        updatedTs = LocalDateTime.now();
        createdBy = createdBy == null ? "admin" : createdBy;
        updatedBy = updatedBy == null ? "admin" : updatedBy;
    }

    @PreUpdate
    public void preUpdate() {
        updatedTs = LocalDateTime.now();
        updatedBy = updatedBy == null ? "admin" : updatedBy;
    }


}
