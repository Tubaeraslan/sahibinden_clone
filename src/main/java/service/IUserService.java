package service;

import dto.responseDto.UserResponseDto;
import dto.requestDto.UserRequestDto;
import entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Integer id);

    UserResponseDto addUser(UserRequestDto userRequest);

    void deleteUser(Integer id);

}
