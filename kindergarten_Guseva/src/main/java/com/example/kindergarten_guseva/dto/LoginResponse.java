package com.example.kindergarten_guseva.dto;

/**
 * Класс для передачи ответа после попытки входа пользователя.
 *
 * <p>Содержит сообщение о результате входа.</p>
 */
public class LoginResponse {
    private String message;

    /**
     * Конструктор для создания объекта с сообщением.
     *
     * @param message сообщение о результате входа
     */
    public LoginResponse(String message) {
        this.message = message;
    }

    /**
     * Возвращает сообщение о результате входа.
     *
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }
}
