package com.example.kindergarten_guseva.dto;

/**
 * DTO для передачи данных входа пользователя.
 *
 * <p>Содержит email и пароль, необходимые для аутентификации.</p>
 */
public class LoginRequest {
    private String email;
    private String password;

    /**
     * Возвращает email пользователя.
     *
     * @return email пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает email пользователя.
     *
     * @param email email пользователя
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
