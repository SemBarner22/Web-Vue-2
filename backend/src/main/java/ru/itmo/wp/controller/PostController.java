package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.validator.CommentValidator;
import ru.itmo.wp.form.validator.PostValidator;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController extends ApiController {
    private final PostService postService;
    private final PostValidator postValidator;
    private final CommentValidator commentValidator;

    public PostController(PostService postService, PostValidator postValidator, CommentValidator commentValidator) {
        this.postService = postService;
        this.postValidator = postValidator;
        this.commentValidator = commentValidator;
    }

    @InitBinder({"post", "comment"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(postValidator);
       // binder.addValidators(commentValidator);
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @PostMapping("posts")
    public void addPost(@RequestBody @Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }

        postService.addPost(post);
    }

//    @GetMapping("comments")
//    public List<Comment> findComments(long postId) {
//        Post post = postService.findById(postId);
//        return post == null ? null : post.getComments();
//    }

    @PostMapping("comments")
    public void addComment(@RequestBody @Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }

        postService.addComment(comment);
    }
}
