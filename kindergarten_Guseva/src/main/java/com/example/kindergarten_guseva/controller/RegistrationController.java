package com.example.kindergarten_guseva.controller;

import com.example.kindergarten_guseva.dto.UserRegistrationDto;
import com.example.kindergarten_guseva.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки запросов, связанных с регистрацией пользователей.
 *
 * <p>Этот контроллер предоставляет эндпоинты для отображения формы регистрации и обработки данных регистрации.</p>
 */
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserService userService;

    /**
     * Конструктор для внедрения зависимости {@link UserService}.
     *
     * @param userService сервис для работы с пользователями
     */
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает форму регистрации.
     *
     * @return строка с именем представления формы регистрации
     */
    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    /**
     * Обрабатывает регистрацию нового пользователя.
     *
     * <p>Если регистрация успешна, возвращается статус 201 (CREATED) с сообщением.
     * В случае ошибки возвращается статус 400 (BAD REQUEST) с соответствующим сообщением.</p>
     *
     * @param registrationDto данные для регистрации пользователя
     * @return HTTP-ответ с результатом регистрации
     */
    @PostMapping
    public ResponseEntity<String> registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.save(registrationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Регистрация успешна.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Адрес электронной почты уже используется");
        }
    }
}
