package com.example.kindergarten_guseva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс для настройки Spring Security.
 *
 * <p>Этот класс настраивает безопасность приложения, включая обработку маршрутов,
 * авторизацию, аутентификацию и использование шифрования паролей.</p>
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

    /**
     * Определяет бин для шифрования паролей с использованием {@link BCryptPasswordEncoder}.
     *
     * <p>Этот шифратор используется для безопасного хранения паролей.</p>
     *
     * @return экземпляр {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Определяет цепочку фильтров безопасности для обработки HTTP-запросов.
     *
     * <p>Этот метод настраивает маршруты, их доступность и конфигурацию логина и логаута.</p>
     *
     * @param http объект {@link HttpSecurity} для настройки безопасности
     * @return настроенная цепочка фильтров {@link SecurityFilterChain}
     * @throws Exception если возникает ошибка конфигурации
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/new").hasRole("ADMIN")
                                .requestMatchers("/edit/**").hasRole("ADMIN")
                                .requestMatchers("/save").hasRole("ADMIN")
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/api/v1/medicine/**").hasRole("ADMIN")
                                .requestMatchers("/favicon.ico").permitAll()
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/registration").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/admin/**").permitAll()
                                .anyRequest().authenticated()
                )
                // Настройка формы логина
                .formLogin(form -> form
                        .loginPage("/login") // URL страницы логина
                        .loginProcessingUrl("/login")// URL обработки логина
                        .defaultSuccessUrl("/index", true)// URL перенаправления после успешного входа
                        .permitAll()// Доступ для всех
                );

        return http.build();
    }
}
