package com.example.kindergarten_guseva.repository;

import java.util.List;

import com.example.kindergarten_guseva.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий для работы с сущностью {@link Child}.
 *
 * <p>Интерфейс расширяет {@link JpaRepository}, что предоставляет доступ
 * к стандартным методам для выполнения операций с базой данных, таких как
 * сохранение, удаление и поиск по идентификатору.</p>
 */
public interface ChildRepository extends JpaRepository<Child, Long> {

    /**
     * Выполняет поиск записей {@link Child}, соответствующих указанному ключевому слову.
     *
     * <p>Поиск осуществляется по всем полям сущности, включая id, ФИО, группу,
     * ФИО воспитателя, дату посещения и дату рождения. Сравнение выполняется с помощью
     * операции LIKE, что позволяет находить частичные совпадения.</p>
     *
     * @param keyword ключевое слово для поиска
     * @return список записей, соответствующих ключевому слову
     */
    @Query("SELECT p FROM Child p WHERE CONCAT(p.id, ' ', p.title, ' ', p.groupp, ' ', p.mentor, ' ', p.issueDate, ' ', p.returnDate) LIKE %?1%")
    List<Child> search(String keyword);
}
