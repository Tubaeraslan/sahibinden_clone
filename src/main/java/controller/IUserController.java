package controller;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUserController {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(@PathVariable Integer id);

    UserResponseDto addUser(@RequestBody UserRequestDto user);

    void deleteUser(@PathVariable Integer id);
}
