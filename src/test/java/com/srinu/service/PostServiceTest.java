package com.srinu.service;

import com.srinu.entity.Post;
import com.srinu.repository.PostRepository;
import com.srinu.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Optional;

public class PostServiceTest {

    PostService postService = new PostService();
    PostRepository postRepository = Mockito.mock(PostRepository.class);

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(postService, "postRepository", postRepository);
    }

    @Test
    public void testCreate() {
        Post post = new Post();
        post.setData("data");
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.create(post);
        Assertions.assertEquals(post, savedPost);
    }

    @Test
    public void testCreateWithReference() {
        Post post = new Post();
        post.setData("data");
        Post reference = new Post();
        reference.setId("1");
        reference.setData("data2");
        post.setReferencePost(reference);
        Mockito.when(postRepository.findById("1")).thenReturn(Optional.of(reference));
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.create(post);
        Assertions.assertEquals(post, savedPost);
        Assertions.assertEquals(reference.getData(), savedPost.getReferencePost().getData());
    }

    @Test
    public void testUpdate() {
        Post post = new Post();
        post.setData("data");
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.update(post);
        Assertions.assertEquals(post, savedPost);
    }

    @Test
    public void testUpdateWithReference() {
        Post post = new Post();
        post.setData("data");
        Post reference = new Post();
        reference.setId("1");
        reference.setData("data2");
        post.setReferencePost(reference);
        Mockito.when(postRepository.findById("1")).thenReturn(Optional.of(reference));
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.update(post);
        Assertions.assertEquals(post, savedPost);
        Assertions.assertEquals(reference.getData(), savedPost.getReferencePost().getData());
    }

    @Test
    public void testGet() {
        Post post = new Post();
        Mockito.when(postRepository.findById("1")).thenReturn(Optional.of(post));

        Assertions.assertEquals(post, postService.get("1"));
    }

    @Test
    public void testGetNull() {
        Post post = new Post();
        Mockito.when(postRepository.findById("1")).thenReturn(Optional.empty());
        Assertions.assertNull(postService.get("1"));
    }

    @Test
    public void testGetAll() {
        Post post = new Post();
        Mockito.when(postRepository.findAllById(Mockito.anyCollection())).thenReturn(Collections.singletonList(post));

        Assertions.assertEquals(post, postService.getAll(Collections.singletonList("1")).get(0));
    }

    @Test
    public void testDeleteHard() {
        postService.delete("1", false);
        Mockito.verify(postRepository).deleteById("1");
        Mockito.verify(postRepository, Mockito.times(0)).softDelete("1");
    }

    @Test
    public void testDeleteSoft() {
        postService.delete("1", true);
        Mockito.verify(postRepository, Mockito.times(0)).deleteById("1");
        Mockito.verify(postRepository).softDelete("1");
    }


}
