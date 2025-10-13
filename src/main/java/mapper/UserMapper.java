package mapper;

import dto.requestDto.UserRequestDto;
import dto.responseDto.UserResponseDto;
import entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // DTO -> Entity
    @Mapping(target = "id", ignore = true) // ID database tarafından üretilecek
    User toEntity(UserRequestDto dto);

    // Entity -> DTO
    UserResponseDto toDto(User user);
}
