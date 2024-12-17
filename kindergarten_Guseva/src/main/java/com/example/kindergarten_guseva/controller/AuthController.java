package com.example.kindergarten_guseva.controller;

import com.example.kindergarten_guseva.dto.LoginRequest;
import com.example.kindergarten_guseva.dto.LoginResponse;
import com.example.kindergarten_guseva.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки запросов аутентификации.
 *
 * <p>Этот контроллер предоставляет эндпоинт для входа пользователя в систему.</p>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Конструктор для внедрения зависимости {@link AuthService}.
     *
     * @param authService сервис для выполнения операций аутентификации
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Обрабатывает запрос на вход пользователя.
     *
     * <p>Метод принимает данные для входа в теле запроса и проверяет их с помощью {@link AuthService}.
     * Если аутентификация успешна, возвращается успешный ответ.
     * В противном случае возвращается статус 401 (UNAUTHORIZED).</p>
     *
     * @param loginRequest объект с данными для входа (логин и пароль)
     * @return ответ с сообщением об успешной или неудачной аутентификации
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.authenticate(loginRequest);
        if (isAuthenticated) {
            return ResponseEntity.ok(new LoginResponse("successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid"));
        }
    }
}