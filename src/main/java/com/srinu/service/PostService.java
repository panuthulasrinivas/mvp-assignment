package com.srinu.service;

import com.srinu.entity.Post;
import com.srinu.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Service logic to create post
     * @param post
     * @return
     */
    @Transactional
    public Post create(Post post) {
        if(post.getReferencePost()!=null && post.getReferencePost().getId()!=null){
            Optional<Post> referencePost=postRepository.findById(post.getReferencePost().getId());
            if(referencePost.isPresent()){
                post.setReferencePost(referencePost.get());
            }
        }
        return postRepository.save(post);
    }

    /**
     * service logic to update post
     * @param post
     * @return
     */
    @Transactional
    public Post update(Post post) {
        if(post.getReferencePost()!=null && post.getReferencePost().getId()!=null){
            Optional<Post> referencePost=postRepository.findById(post.getReferencePost().getId());
            if(referencePost.isPresent()){
                post.setReferencePost(referencePost.get());
            }
        }
        return postRepository.save(post);
    }

    /**
     * service logic to get post using id
     * @param id
     * @return
     */
    public Post get(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            return optionalPost.get();
        }
        return null;
    }

    /**
     * Service logic to get multiple posts using list of ids
     * @param ids
     * @return
     */
    public List<Post> getAll(List<String> ids) {
        return postRepository.findAllById(ids);
    }

    /**
     * service logic to delete post
     * @param id
     * @param isSoftDelete
     */
    @Transactional
    public void delete(String id, boolean isSoftDelete) {
        if (isSoftDelete) {
            postRepository.softDelete(id);
        } else {
            postRepository.deleteById(id);
        }
    }
}
