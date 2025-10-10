package service.impl;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import entities.User;
import exception.BadRequestException;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private UserResponseDto convertToResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Integer id) {
       User user = userRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("User not found with id:" + id));
       return convertToResponseDto(user);
    }

    //add update method

    @Override
    public UserResponseDto addUser(UserRequestDto userRequest) {
        boolean nameExists = userRepository.findAll()
                .stream()
                .anyMatch(u -> u.getUserName().equalsIgnoreCase(userRequest.getUserName()));
        if (nameExists) {
            throw new BadRequestException("User with this name already exists!");
        }
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        User saved = userRepository.save(user);
        return convertToResponseDto(saved);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id:" + id);
        }
        userRepository.deleteById(id);
    }
}
