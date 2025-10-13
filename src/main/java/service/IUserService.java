package service;

import dto.responseDto.UserResponseDto;
import dto.requestDto.UserRequestDto;
import entities.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    Page<UserResponseDto> getAllUsers(Integer page, Integer size);

    UserResponseDto getUserById(Integer id);

    UserResponseDto addUser(UserRequestDto userRequest);

    void deleteUser(Integer id);

}
