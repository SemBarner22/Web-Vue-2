package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

@Component
public class CommentValidator implements Validator {
    private final UserService userService;
    private final PostService postService;

    public CommentValidator(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public boolean supports(Class<?> clazz) {
        return Comment.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
//            Comment comment = (Comment) target;
//            if (userService.findByLogin(comment.getUser().getLogin()) == null) {
//                errors.reject("comment.author-invalid", "Author is invalid.");
//            }
//            if (postService.findById(comment.getPost().getId()) == null) {
//                errors.reject("comment.post-invalid", "Related post does not exist.");
//            }
        }
    }
}
