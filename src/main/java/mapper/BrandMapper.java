package mapper;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//java doc
@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)

    //DTO ->ENTITY
    Brand toEntity(BrandRequestDto dto);

    //ENTITY->DTO
    BrandResponseDto toDto(Brand brand);
}
