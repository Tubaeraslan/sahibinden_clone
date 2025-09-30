package controller;

import entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUserController {

    List<User> getAllUsers();

    User getUserById(@PathVariable Integer id);

    User addUser(@RequestBody User user);

    void deleteUser(@PathVariable Integer id);
}
