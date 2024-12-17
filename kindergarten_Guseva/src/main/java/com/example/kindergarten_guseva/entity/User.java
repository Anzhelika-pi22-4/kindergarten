package com.example.kindergarten_guseva.entity;

import jakarta.persistence.*;

/**
 * Сущность "Пользователь" для представления данных о пользователях системы.
 *
 * <p>Класс обозначен как сущность с уникальным ограничением на столбец "email".
 * Содержит информацию о пользователе, включая идентификатор, имя, фамилию,
 * адрес электронной почты, пароль и роль.</p>
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    /**
     * Конструктор без параметров.
     */
    public User() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param id        идентификатор пользователя
     * @param firstName имя пользователя
     * @param lastName  фамилия пользователя
     * @param email     адрес электронной почты
     * @param password  пароль
     * @param role      роль пользователя
     */
    public User(Long id, String firstName, String lastName, String email, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Возвращает идентификатор пользователя.
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор пользователя.
     *
     * @param id идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param firstName имя
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Возвращает фамилию пользователя.
     *
     * @return фамилия
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Устанавливает фамилию пользователя.
     *
     * @param lastName фамилия
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Возвращает адрес электронной почты пользователя.
     *
     * @return адрес электронной почты
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает адрес электронной почты пользователя.
     *
     * @param email адрес электронной почты
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает роль пользователя.
     *
     * @return роль
     */
    public String getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль
     */
    public void setRole(String role) {
        this.role = role;
    }
}
