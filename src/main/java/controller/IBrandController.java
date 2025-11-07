package controller;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IBrandController {

    Page<BrandResponseDto> getAllBrands(Integer page, Integer size);

    BrandResponseDto getBrandById(@PathVariable Integer id);

    BrandResponseDto addBrand(@RequestBody BrandRequestDto brandRequest);

    BrandResponseDto updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandRequestDto brandRequest);

    void deleteBrand(@PathVariable Integer id);
}
