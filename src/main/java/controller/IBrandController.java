package controller;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBrandController {

    List<BrandResponseDto> getAllBrands();

    BrandResponseDto getBrandById(@PathVariable Integer id);

    BrandResponseDto addBrand(@RequestBody BrandRequestDto brandRequest);

    void deleteBrand(@PathVariable Integer id);
}
