package com.example.kindergarten_guseva.service.impl;

import com.example.kindergarten_guseva.dto.UserRegistrationDto;
import com.example.kindergarten_guseva.entity.User;
import com.example.kindergarten_guseva.repository.UserRepository;
import com.example.kindergarten_guseva.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Реализация сервиса для работы с пользователями.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Конструктор для инициализации зависимостей.
     *
     * @param userRepository репозиторий пользователей
     * @param passwordEncoder кодировщик паролей
     */
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Сохраняет нового пользователя на основе данных регистрации.
     *
     * @param registrationDto DTO с данными регистрации пользователя
     * @return сохранённый пользователь
     */
    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user);
    }

    /**
     * Возвращает список всех пользователей.
     *
     * @return список пользователей
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Загружает данные пользователя по имени пользователя (email).
     *
     * @param username email пользователя
     * @return данные пользователя
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRoleToAuthorities(user.getRole())
        );
    }

    /**
     * Преобразует роль пользователя в коллекцию полномочий.
     *
     * @param role роль пользователя
     * @return коллекция полномочий
     */
    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * Находит пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return найденный пользователь
     * @throws RuntimeException если пользователь не найден
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Обновляет данные пользователя.
     *
     * @param user объект пользователя с обновлёнными данными
     * @return обновлённый пользователь
     */
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     */
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
