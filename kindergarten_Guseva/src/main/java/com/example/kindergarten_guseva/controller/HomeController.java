package com.example.kindergarten_guseva.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для обработки GET-запросов к HTML-страницам.
 *
 * <p>Этот контроллер отвечает за маршрутизацию на страницы входа, регистрации, главной страницы
 * и страницы администратора.</p>
 */
@Controller
public class HomeController {

    /**
     * Обрабатывает запрос на отображение страницы входа.
     *
     * @return имя представления страницы входа
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Обрабатывает запрос на отображение страницы регистрации.
     *
     * @return имя представления страницы регистрации
     */
    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    /**
     * Обрабатывает запрос на отображение главной страницы.
     *
     * <p>Если пользователь аутентифицирован, его имя пользователя добавляется в модель.
     * Если нет, отображается сообщение "Не понятно кто".</p>
     *
     * @param model объект для передачи данных в представление
     * @return имя представления главной страницы
     */
    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            String username = auth.getName();
            model.addAttribute("username", username);
        } else {
            model.addAttribute("username", "Неизвестный");
        }

        return "index";
    }

    /**
     * Обрабатывает запрос на отображение страницы администратора.
     *
     * @param model объект для передачи данных в представление
     * @return имя представления страницы администратора
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }
}
