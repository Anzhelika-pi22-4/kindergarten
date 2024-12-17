package com.example.kindergarten_guseva.controller;

import com.example.kindergarten_guseva.entity.User;
import com.example.kindergarten_guseva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Контроллер для управления пользователями в админской панели.
 *
 * <p>Этот контроллер предоставляет API-запросы для изменения ролей пользователей
 * и удаления пользователей.</p>
 */
@Controller
@RequestMapping("api/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * Обновляет роль пользователя по его ID.
     *
     * <p>Этот эндпоинт позволяет изменить роль пользователя, передав её в теле запроса.</p>
     *
     * @param userData карта с данными пользователя (id и новая роль)
     * @return статусный код HTTP-ответа
     */
    @PostMapping("/update")
    public ResponseEntity<Void> updateUserRole(@RequestBody Map<String, Object> userData) {
        Long userId = Long.valueOf(userData.get("id").toString());
        String newRole = userData.get("role").toString();
        User user = userService.findById(userId);
        if (user != null) {
            user.setRole(newRole);
            userService.update(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет пользователя по его ID.
     *
     * <p>Этот эндпоинт удаляет пользователя, если он существует в базе данных.</p>
     *
     * @param id идентификатор пользователя, которого нужно удалить
     * @return статусный код HTTP-ответа
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}