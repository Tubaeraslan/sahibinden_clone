package service;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBrandService {
    List<BrandResponseDto> getAllBrands();

    BrandResponseDto getBrandById(Integer id);

    BrandResponseDto addBrand(BrandRequestDto brandRequest);

    BrandResponseDto updateBrand(Integer id, BrandRequestDto brandRequest);

    void deleteBrand(Integer id);
}
