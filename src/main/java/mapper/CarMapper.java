package mapper;

import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import entities.Brand;
import entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", ignore = true) // Yeni eklemelerde id yok
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrandIdToBrand")
    // DTO -> ENTITY
    Car toEntity(CarRequestDto dto);

    // ENTITY -> DTO
    CarResponseDto toDto(Car car);

    // BrandId'yi Brand objesine çevir
    @Named("mapBrandIdToBrand")
    default Brand mapBrandIdToBrand(Integer brandId) {
        if (brandId == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setId(brandId);
        return brand;
    }
}
