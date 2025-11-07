package controller.impl;

import controller.IUserController;
import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import service.IUserService;


@RestController
@RequestMapping("/rest/api/users")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping
    public Page<UserResponseDto> getAllUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
        return userService.getAllUsers(page,size);
    }

    @Override
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @Override
    @PostMapping
    public UserResponseDto addUser(@Valid @RequestBody UserRequestDto userRequest){
        return userService.addUser(userRequest);
    }

    @Override
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Integer id, @Valid @RequestBody UserRequestDto userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
