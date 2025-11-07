package service;

import dto.responseDto.UserResponseDto;
import dto.requestDto.UserRequestDto;
import org.springframework.data.domain.Page;

public interface IUserService {

    Page<UserResponseDto> getAllUsers(Integer page, Integer size);

    UserResponseDto getUserById(Integer id);

    UserResponseDto addUser(UserRequestDto userRequest);

    UserResponseDto updateUser(Integer id, UserRequestDto userRequest);

    void deleteUser(Integer id);

}
