package com.example.kindergarten_guseva;

/**
 * Кастомное исключение для обработки ошибок в приложении.
 */
public class MyException extends RuntimeException {

    /**
     * Создаёт новое исключение с заданным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public MyException(String message) {
        super(message);
    }
}
