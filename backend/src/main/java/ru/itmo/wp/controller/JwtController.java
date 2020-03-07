package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.EnterUserCredentials;
import ru.itmo.wp.form.validator.EnterUserCredentialsValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1")
public class JwtController extends ApiController {
    private final JwtService jwtService;
    private final EnterUserCredentialsValidator enterUserCredentialsValidator;

    public JwtController(JwtService jwtService, EnterUserCredentialsValidator enterUserCredentialsValidator) {
        this.jwtService = jwtService;
        this.enterUserCredentialsValidator = enterUserCredentialsValidator;
    }

    @InitBinder("enterUserCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(enterUserCredentialsValidator);
    }

    @PostMapping("jwt")
    public String createJwt(@RequestBody @Valid EnterUserCredentials enterUserCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }

        return jwtService.create(enterUserCredentials.getLogin(), enterUserCredentials.getPassword());
    }
}
