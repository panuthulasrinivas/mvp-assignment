package com.srinu.repository;

import com.srinu.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, String> {

    @Modifying
    @Query(value = "update Post set isDeleted=true where id = :id")
    int softDelete(@Param("id") String id);
}
