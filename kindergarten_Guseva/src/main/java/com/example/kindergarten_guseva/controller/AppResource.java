package com.example.kindergarten_guseva.controller;

import com.example.kindergarten_guseva.MyException;
import com.example.kindergarten_guseva.entity.Child;
import com.example.kindergarten_guseva.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки API-запросов, связанных с детьми.
 *
 * <p>Этот контроллер предоставляет эндпоинты для получения данных о ребенке по ID,
 * а также для создания новых записей о детях.</p>
 */
@RestController
@RequestMapping("/api/v1/child")
public class AppResource {

    private final ChildService childService;

    /**
     * Конструктор для внедрения зависимости {@link ChildService}.
     *
     * @param childService сервис для работы с данными о детях
     */
    public AppResource(ChildService childService) {
        this.childService = childService;
    }

    /**
     * Получает данные о ребенке по его ID.
     *
     * <p>Если ребенок с таким ID не найден, выбрасывается исключение {@link MyException} с сообщением "not found".</p>
     *
     * @param id идентификатор ребенка
     * @return данные о ребенке
     * @throws MyException если ребенок не найден
     */
    @GetMapping("/{id}")
    public Child findById(@PathVariable long id) {
        return childService.findById(id).orElseThrow(() -> new MyException("not found"));
    }

    /**
     * Создает нового ребенка в системе.
     *
     * @param child объект ребенка, который будет сохранен
     * @return сохраненный объект ребенка
     */
    @PostMapping
    public Child create(@RequestBody Child child) {
        return childService.save(child);
    }
}
