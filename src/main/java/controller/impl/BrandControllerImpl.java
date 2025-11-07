package controller.impl;

import controller.IBrandController;
import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import service.IBrandService;

@RestController
@RequestMapping("/rest/api/brands")
public class BrandControllerImpl implements IBrandController {

    @Autowired
    private IBrandService brandService;


    @Override
    @GetMapping
    public Page<BrandResponseDto> getAllBrands(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
        return brandService.getAllBrands(page, size);
    }

    @Override
    @GetMapping("/{id}")
    public BrandResponseDto getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id);
    }

    @Override
    @PostMapping
    public BrandResponseDto addBrand(@Valid @RequestBody BrandRequestDto brandRequest) {
        return brandService.addBrand(brandRequest);
    }

    @Override
    @PutMapping("/{id}")
    public BrandResponseDto updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandRequestDto brandRequest) {
        return brandService.updateBrand(id, brandRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
    }
}
