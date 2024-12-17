package com.example.kindergarten_guseva.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Сущность "Ребенок" для представления данных о детях.
 *
 * <p>Класс обозначен как сущность для использования с ORM JPA.
 * Представляет данные о ребенке, включая уникальный идентификатор, название,
 * группу, воспитателя, дату посещения и дату рождения.</p>
 */
@Entity
public class Child {
    private Long id;
    private String title;
    private String groupp;
    private String mentor;
    private String issueDate;
    private String returnDate;

    /**
     * Конструктор без параметров.
     */
    public Child() {
    }

    /**
     * Возвращает уникальный идентификатор ребенка.
     *
     * @return идентификатор ребенка
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор ребенка.
     *
     * @param id идентификатор ребенка
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает ФИО.
     *
     * @return ФИО
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает ФИО.
     *
     * @param title ФИО
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает группу.
     *
     * @return группа
     */
    public String getGroupp() {
        return groupp;
    }

    /**
     * Устанавливает группу.
     *
     * @param groupp группа
     */
    public void setGroupp(String groupp) {
        this.groupp = groupp;
    }

    /**
     * Возвращает воспитателя.
     *
     * @return воспитателя
     */
    public String getMentor() {
        return mentor;
    }

    /**
     * Устанавливает воспитателя.
     *
     * @param mentor воспитатель
     */
    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    /**
     * Возвращает дату посещения.
     *
     * @return дата посещения
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Устанавливает дату посещения.
     *
     * @param issueDate дата посещения
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Возвращает дату рождения.
     *
     * @return дата рождения
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Устанавливает дату рождения.
     *
     * @param returnDate дата рождения
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
