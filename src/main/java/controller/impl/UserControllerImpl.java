package controller.impl;

import controller.IUserController;
import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/users")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @Override
    @PostMapping
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequest){
        return userService.addUser(userRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
