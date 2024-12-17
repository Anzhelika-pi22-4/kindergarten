package com.example.kindergarten_guseva.repository;

import com.example.kindergarten_guseva.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью {@link User}.
 *
 * <p>Этот интерфейс расширяет {@link JpaRepository}, предоставляя доступ
 * к стандартным методам для выполнения операций с базой данных, таким как
 * сохранение, удаление и поиск по идентификатору.</p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Ищет пользователя по адресу электронной почты.
     *
     * @param email адрес электронной почты пользователя
     * @return объект {@link User} с указанным адресом электронной почты, или {@code null}, если пользователь не найден
     */
    User findByEmail(String email);
}
