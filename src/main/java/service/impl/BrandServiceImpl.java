package service.impl;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BrandRepository;
import service.IBrandService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    private BrandResponseDto convertToResponseDto(Brand brand){
        BrandResponseDto dto = new BrandResponseDto();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        return dto;
    }

    @Override
    public List<BrandResponseDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto getBrandById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.map(this::convertToResponseDto).orElse(null);
    }

    @Override
    public BrandResponseDto addBrand(BrandRequestDto brandRequest) {
        Brand brand = new Brand();
        brand.setName(brandRequest.getName());
        Brand saved = brandRepository.save(brand);
        return convertToResponseDto(saved);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }
}
