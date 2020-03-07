package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.RegisterUserCredentials;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findByLogin(String login) {
        return login == null ? null : userRepository.findByLogin(login);
    }

    public User register(RegisterUserCredentials registerUserCredentials) {
        User user = new User();
        user.setLogin(registerUserCredentials.getLogin());
        user.setName(registerUserCredentials.getName());
        user.setPasswordSHA("");
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerUserCredentials.getLogin(), registerUserCredentials.getPassword());

        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
