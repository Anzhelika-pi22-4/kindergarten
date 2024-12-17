package com.example.kindergarten_guseva.service;

import com.example.kindergarten_guseva.dto.UserRegistrationDto;
import com.example.kindergarten_guseva.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService extends UserDetailsService {

    /**
     * Сохраняет нового пользователя на основе данных для регистрации.
     *
     * @param registrationDto DTO с данными регистрации пользователя
     * @return сохранённый пользователь
     */
    User save(UserRegistrationDto registrationDto);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список пользователей
     */
    List<User> findAll();

    /**
     * Находит пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return найденный пользователь
     */
    User findById(Long id);

    /**
     * Обновляет данные пользователя.
     *
     * @param user объект пользователя с обновлёнными данными
     * @return обновлённый пользователь
     */
    User update(User user);

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     */
    void delete(Long id);
}
