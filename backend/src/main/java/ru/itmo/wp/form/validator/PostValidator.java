package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.UserService;

@Component
public class PostValidator implements Validator {
    private final UserService userService;

    public PostValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return Post.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            Post post = (Post) target;
            if (userService.findByLogin(post.getUser().getLogin()) == null) {
                errors.reject("password.author-invalid", "Author is invalid.");
            }
        }
    }
}
