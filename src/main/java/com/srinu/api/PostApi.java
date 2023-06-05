package com.srinu.api;

import com.srinu.entity.Post;
import com.srinu.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to manage mvp posts
 */
@RestController
@RequestMapping(value = "/v0/post")
public class PostApi {

    @Autowired
    PostService postService;

    /**
     * Api to create post
     * @param post
     * @return
     */
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post));
    }

    /**
     * Api to update post
     * @param post
     * @return
     */
    @PutMapping
    public ResponseEntity<Post> update(@RequestBody Post post) {
        if (StringUtils.isAllEmpty(post.getId())) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(postService.update(post));
        }
    }

    /**
     * Api to get post using single id
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity<Post> get(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(postService.get(id));
    }

    /**
     * Api to get multiple posts using list of ids
     * @param ids
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Post>> getAll(@RequestParam(name = "ids")  List<String> ids) {
        return ResponseEntity.ok(postService.getAll(ids));
    }

    /**
     * Api to delete posts.
     * In production most of the tim we use soft delete
     * in blow api i have implemented both soft and hard delete
     * @param isSoftDelete true is soft delete , false for hard delete
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "isSoftDelete", required = false, defaultValue = "false") Boolean isSoftDelete, @RequestParam(name = "id") String id) {
        postService.delete(id, isSoftDelete);
        return ResponseEntity.noContent().build();
    }


}
