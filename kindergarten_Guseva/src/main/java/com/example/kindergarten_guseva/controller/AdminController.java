package com.example.kindergarten_guseva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.kindergarten_guseva.service.UserService;
import com.example.kindergarten_guseva.entity.User;
import java.util.List;

/**
 * Контроллер для управления данными пользователей в админской панели.
 *
 * <p>Этот контроллер предоставляет API-запросы для работы с пользователями,
 * доступные только администраторам.</p>
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    /**
     * Конструктор контроллера, внедряющий {@link UserService}.
     *
     * @param userService сервис для работы с данными пользователей
     */
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получает список всех пользователей.
     *
     * <p>Эндпоинт доступен только пользователям с ролью ADMIN.</p>
     *
     * @return список пользователей в формате {@link ResponseEntity}
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}

