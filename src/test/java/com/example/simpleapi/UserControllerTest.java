package com.example.simpleapi;

import com.example.simpleapi.controller.UserController;
import com.example.simpleapi.model.User;
import com.example.simpleapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Test
    public void testListUsers() {
        UserService svc = new UserService();
        UserController controller = new UserController(svc);

        List<User> list = controller.list();

        assertTrue(list.size() >= 2);
    }

    @Test
    public void testGetUser() {
        UserService svc = new UserService();
        UserController controller = new UserController(svc);

        ResponseEntity<User> response = controller.get(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Alice", response.getBody().getName());
    }

    @Test
    public void testCreateUser() {
        UserService svc = new UserService();
        UserController controller = new UserController(svc);

        User user = new User(null, "Hitesh", "hitesh@example.com");

        ResponseEntity<User> response = controller.create(user);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Hitesh", response.getBody().getName());
        assertNotNull(response.getHeaders().getLocation());
    }

    @Test
    public void testUpdateUser() {
        UserService svc = new UserService();
        UserController controller = new UserController(svc);

        User updated = new User(null, "Updated Name", "updated@example.com");

        ResponseEntity<User> response = controller.update(1L, updated);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Updated Name", response.getBody().getName());
    }

    @Test
    public void testDeleteUser() {
        UserService svc = new UserService();
        UserController controller = new UserController(svc);

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(204, response.getStatusCode().value());
        assertNull(svc.findById(1L));
    }
}
