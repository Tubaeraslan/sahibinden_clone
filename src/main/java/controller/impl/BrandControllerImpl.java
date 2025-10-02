package controller.impl;

import controller.IBrandController;
import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IBrandService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/brands")
public class BrandControllerImpl implements IBrandController {

    @Autowired
    private IBrandService brandService;


    @Override
    @GetMapping
    public List<BrandResponseDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @Override
    @GetMapping("/{id}")
    public BrandResponseDto getBrandById(Integer id) {
        return brandService.getBrandById(id);
    }

    @Override
    @PostMapping
    public BrandResponseDto addBrand(BrandRequestDto brandRequest) {
        return brandService.addBrand(brandRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteBrand(Integer id) {
        brandService.deleteBrand(id);
    }
}
