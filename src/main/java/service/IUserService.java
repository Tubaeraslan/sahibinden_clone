package service;

import entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User addUser(User user);

    void deleteUser(Integer id);

}
