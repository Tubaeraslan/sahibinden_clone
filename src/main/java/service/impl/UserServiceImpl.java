package service.impl;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import entities.User;
import exception.BadRequestException;
import exception.ResourceNotFoundException;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponseDto> getAllUsers(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest)
                .map(userMapper::toDto);
    }

    @Override
    public UserResponseDto getUserById(Integer id) {
       User user = userRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("User not found with id:" + id));
       return userMapper.toDto(user);
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
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public UserResponseDto updateUser(Integer id, UserRequestDto userRequest) {
        //Mevcut kullanıcı var mı kontrol et
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Aynı kullanıcı adından başka biri var mı kontrol et (duplicate check)
        boolean nameExists = userRepository.findAll()
                .stream()
                .anyMatch(u -> !u.getId().equals(id) &&
                        u.getUserName().equalsIgnoreCase(userRequest.getUserName()));

        if (nameExists) {
            throw new BadRequestException("User with this name already exists!");
        }

        existingUser.setUserName(userRequest.getUserName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User updated = userRepository.save(existingUser);
        return userMapper.toDto(updated);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id:" + id);
        }
        userRepository.deleteById(id);
    }
}
