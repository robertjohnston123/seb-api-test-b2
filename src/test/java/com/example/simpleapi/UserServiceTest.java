package com.example.simpleapi;

import com.example.simpleapi.model.User;
import com.example.simpleapi.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void testFindAll() {
        UserService svc = new UserService();
        List<User> users = svc.findAll();

        assertTrue(users.size() >= 2);  // Alice and Bob seeded
    }

    @Test
    public void testSaveUser() {
        UserService svc = new UserService();
        User user = new User(null, "Hitesh", "hitesh@test.com");
        User saved = svc.save(user);

        assertNotNull(saved.getId());
        assertEquals("Hitesh", saved.getName());
    }

    @Test
    public void testFindById() {
        UserService svc = new UserService();
        User u = svc.findById(1L);

        assertNotNull(u);
        assertEquals("Alice", u.getName());
    }

    @Test
    public void testDeleteUser() {
        UserService svc = new UserService();
        boolean deleted = svc.delete(1L);

        assertTrue(deleted);
        assertNull(svc.findById(1L));
    }
}
