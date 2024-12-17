package com.example.kindergarten_guseva.service;

import com.example.kindergarten_guseva.dto.LoginRequest;
import com.example.kindergarten_guseva.entity.User;
import com.example.kindergarten_guseva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки аутентификации пользователей.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param userRepository репозиторий для работы с сущностью {@link User}
     * @param passwordEncoder механизм шифрования паролей
     */
    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Выполняет проверку аутентификационных данных пользователя.
     *
     * @param loginRequest объект, содержащий email и пароль пользователя
     * @return {@code true}, если данные корректны, иначе {@code false}
     */
    public boolean authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        return user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
    }
}