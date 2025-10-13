package controller;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import entities.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUserController {

    Page<UserResponseDto> getAllUsers(Integer page, Integer size);

    UserResponseDto getUserById(@PathVariable Integer id);

    UserResponseDto addUser(@RequestBody UserRequestDto user);

    void deleteUser(@PathVariable Integer id);
}
