package com.example.kindergarten_guseva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Класс конфигурации Spring MVC.
 *
 * <p>Этот класс настраивает разрешение локали и контроллеры представлений.</p>
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Добавляет контроллеры представлений в реестр.
     *
     * @param registry реестр, в который можно добавить контроллеры представлений
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Добавьте контроллеры представлений при необходимости
    }

    /**
     * Определяет бин {@link LocaleResolver} для настройки локали по умолчанию.
     *
     * <p>Этот бин использует {@link SessionLocaleResolver} для хранения локали в сессии пользователя.</p>
     *
     * @return настроенный бин {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    /**
     * Определяет бин {@link LocaleChangeInterceptor} для перехвата запросов на смену локали.
     *
     * <p>Этот перехватчик отслеживает параметр "lang" в запросах для изменения локали.</p>
     *
     * @return настроенный бин {@link LocaleChangeInterceptor}
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * Добавляет перехватчики в реестр.
     *
     * <p>Этот метод регистрирует {@link LocaleChangeInterceptor} для обработки запросов на смену локали.</p>
     *
     * @param registry реестр, в который можно добавить перехватчики
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}

