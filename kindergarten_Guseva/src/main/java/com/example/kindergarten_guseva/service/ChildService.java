package com.example.kindergarten_guseva.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.kindergarten_guseva.entity.Child;
import com.example.kindergarten_guseva.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с сущностью {@link Child}.
 * Реализует бизнес-логику приложения, связанную с детьми.
 */
@Service
public class ChildService {

    private final ChildRepository repo;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param repo репозиторий для работы с сущностью {@link Child}
     */
    @Autowired
    public ChildService(ChildRepository repo) {
        this.repo = repo;
    }

    /**
     * Возвращает список детей, соответствующих ключевому слову.
     * Если ключевое слово не задано, возвращает всех детей.
     *
     * @param keyword ключевое слово для поиска
     * @return список детей
     */
    public List<Child> listAll(String keyword) {
        return (keyword != null) ? repo.search(keyword) : repo.findAll();
    }

    /**
     * Сохраняет нового или обновляет существующего ребенка.
     *
     * @param child объект {@link Child} для сохранения
     * @return сохраненный объект {@link Child}
     */
    public Child save(Child child) {
        return repo.save(child);
    }

    /**
     * Возвращает ребенка по идентификатору.
     *
     * @param id идентификатор ребенка
     * @return объект {@link Child}
     * @throws NoSuchElementException если ребенок не найден
     */
    public Child get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    /**
     * Ищет ребенка по идентификатору.
     *
     * @param id идентификатор ребенка
     * @return объект {@link Optional}, содержащий ребенка, если он найден
     */
    public Optional<Child> findById(long id) {
        return repo.findById(id);
    }

    /**
     * Удаляет ребенка по идентификатору.
     *
     * @param id идентификатор ребенка
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
