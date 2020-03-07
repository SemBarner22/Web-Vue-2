package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.RegisterUserCredentials;
import ru.itmo.wp.form.validator.RegisterUserCredentialsValidator;
import ru.itmo.wp.service.UserService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UsersController extends ApiController {
    private final UserService userService;
    private final RegisterUserCredentialsValidator registerUserCredentialsValidator;

    public UsersController(UserService userService, RegisterUserCredentialsValidator registerUserCredentialsValidator) {
        this.userService = userService;
        this.registerUserCredentialsValidator = registerUserCredentialsValidator;
    }

    @InitBinder("registerUserCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerUserCredentialsValidator);
    }

    @GetMapping("users")
    public List<User> findUsers() {
        return userService.findAll();
    }

    @PostMapping("users")
    public void addUser(@RequestBody @Valid RegisterUserCredentials registerUserCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }

        userService.register(registerUserCredentials);
    }
}
