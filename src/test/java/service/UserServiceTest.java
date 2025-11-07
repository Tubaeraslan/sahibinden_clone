package service;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import starter.SahibindenCloneApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SahibindenCloneApplication.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    void testGetUserById_ReturnsCorrectUser_WhenIdExists() {
        UserRequestDto dto = new UserRequestDto();
        dto.setUserName("Tuba");
        dto.setEmail("tuba@example.com");
        dto.setPassword("12345");

        UserResponseDto created = userService.addUser(dto);

        UserResponseDto found = userService.getUserById(created.getId());
        assertEquals("Tuba", found.getUserName());
        assertEquals("tuba@example.com", found.getEmail());
    }

    @Test
    void testGetUserById_ThrowsException_WhenNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(9999));
    }

    @Test
    void testCreateUser_SavesAndReturnsUser() {
        UserRequestDto dto = new UserRequestDto();
        dto.setUserName("Ahmet");
        dto.setEmail("ahmet@example.com");
        dto.setPassword("secret");

        UserResponseDto saved = userService.addUser(dto);

        assertNotNull(saved.getId());
        assertEquals("Ahmet", saved.getUserName());
        assertEquals("ahmet@example.com", saved.getEmail());
    }

    @Test
    void testUpdateUser_ChangesEmailSuccessfully() {
        UserRequestDto dto = new UserRequestDto();
        dto.setUserName("Ali");
        dto.setEmail("ali@oldmail.com");
        dto.setPassword("1234");

        UserResponseDto created = userService.addUser(dto);

        dto.setEmail("ali@newmail.com");
        UserResponseDto updated = userService.updateUser(created.getId(), dto);

        assertEquals("ali@newmail.com", updated.getEmail());
    }

    @Test
    void testDeleteUser_RemovesUserSuccessfully() {
        UserRequestDto dto = new UserRequestDto();
        dto.setUserName("Mehmet");
        dto.setEmail("mehmet@example.com");
        dto.setPassword("123456");

        UserResponseDto saved = userService.addUser(dto);

        userService.deleteUser(saved.getId());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(saved.getId()));
    }
}
