package controller.impl;

import controller.IUserController;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/users")
public class UserControllerImpl implements IUserController {

    private List<User>userList;

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id); // artık Bean listesinden dönüyor
    }

    @Override
    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
