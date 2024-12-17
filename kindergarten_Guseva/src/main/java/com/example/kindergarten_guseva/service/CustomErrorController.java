package com.example.kindergarten_guseva.service;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Кастомный контроллер для обработки ошибок.
 * Перенаправляет пользователей на соответствующие страницы ошибок в зависимости от HTTP-статуса.
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Обрабатывает ошибки и перенаправляет на страницы в зависимости от HTTP-статуса.
     *
     * @param request текущий HTTP-запрос
     * @return имя представления для отображения ошибки
     */
    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403"; // Страница ошибки доступа
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error"; // Страница ошибки "не найдено"
            }
        }
        return "error/error"; // Общая страница ошибок
    }
}
