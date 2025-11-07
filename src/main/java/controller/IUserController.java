package controller;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

public interface IUserController {

    Page<UserResponseDto> getAllUsers(Integer page, Integer size);

    UserResponseDto getUserById(@PathVariable Integer id);

    UserResponseDto addUser(@RequestBody UserRequestDto user);

    UserResponseDto updateUser(@PathVariable Integer id,@RequestBody UserRequestDto userRequest);

    void deleteUser(@PathVariable Integer id);
}
