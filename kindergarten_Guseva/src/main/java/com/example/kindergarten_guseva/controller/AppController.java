package com.example.kindergarten_guseva.controller;

import java.util.List;

import com.example.kindergarten_guseva.service.ChildService;
import com.example.kindergarten_guseva.entity.Child;
import com.example.kindergarten_guseva.entity.User;
import org.springframework.security.core.GrantedAuthority;
import com.example.kindergarten_guseva.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Контроллер для обработки запросов и взаимодействия с данными детей.
 *
 * <p>Этот контроллер управляет отображением страниц, добавлением, редактированием и удалением детей,
 * а также предоставляет информацию о страницах "О нас" и "Проверка".</p>
 */
@Controller
public class AppController {

    @Autowired
    private ChildService service;

    private UserService userService;

    /**
     * Конструктор контроллера для внедрения {@link UserService}.
     *
     * @param userService сервис для работы с пользователями
     */
    public AppController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Отображает страницу с перечнем детей
     *
     * @param model объект для передачи атрибутов на страницу
     * @param keyword ключевое слово для фильтрации детей
     * @return имя представления (страницы)
     */
    @RequestMapping("/index")
    public String viewHome(Model model, @Param("keyword") String keyword, Authentication authentication) {List<Child> listChild = service.listAll(keyword);
        model.addAttribute("listChild", listChild);
        model.addAttribute("keyword", keyword);
        // Получение роли пользователя
        if (authentication != null) {
            String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .findFirst().orElse("ROLE_USER"); // Роль по умолчанию
            model.addAttribute("userRole", role);
        }
        return "index";}

    /**
     * Отображает форму для добавления нового ребенка.
     *
     * @param model объект для передачи атрибутов на страницу
     * @return имя представления (страницы)
     */
    @RequestMapping("/new")
    public String showNewChildForm(Model model) {
        Child child = new Child();
        model.addAttribute("child", child);
        return "new_child";
    }

    /**
     * Сохраняет данные о новом ребенке.
     *
     * @param child объект ребенка для сохранения
     * @return перенаправление на главную страницу
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveChild(@ModelAttribute("child") Child child) {
        service.save(child);
        return "redirect:/";
    }
    /**
     * Отображает форму для редактирования данных о ребенке.
     *
     * @param id идентификатор ребенка
     * @return представление для редактирования данных ребенка
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditChildForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_child");
        Child child = service.get(id);
        mav.addObject("child", child);
        return mav;
    }

    /**
     * Удаляет ребенка по его ID.
     *
     * @param id идентификатор ребенка
     * @return перенаправление на страницу с перечнем детей
     */
    @RequestMapping("/delete/{id}")
    public String deleteChild(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/index";
    }

    /**
     * Отображает страницу "О нас".
     *
     * @return имя представления (страницы)
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
