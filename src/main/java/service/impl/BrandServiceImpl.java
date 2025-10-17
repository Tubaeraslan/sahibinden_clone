package service.impl;

import dto.requestDto.BrandRequestDto;
import dto.responseDto.BrandResponseDto;
import entities.Brand;
import exception.BadRequestException;
import exception.ResourceNotFoundException;
import mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Page<BrandResponseDto> getAllBrands(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return brandRepository.findAll(pageRequest)
                .map(brandMapper::toDto);    //entityleri response a çevir
    }
    @Override
    public BrandResponseDto getBrandById(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Brand not found with id: " + id));
        return brandMapper.toDto(brand);
    }

    @Override
    public BrandResponseDto addBrand(BrandRequestDto brandRequest) {
        boolean nameExists = brandRepository.findAll()
                .stream()
                .anyMatch(b -> b.getName().equalsIgnoreCase(brandRequest.getName()));
        if (nameExists) {
            throw new BadRequestException("Brand with this name already exists!");
        }
        Brand brand = brandMapper.toEntity(brandRequest);
        Brand saved = brandRepository.save(brand);
        //date control
        System.out.println(saved.getCreatedDate());
        System.out.println(saved.getUpdatedDate());
        return brandMapper.toDto(saved);
    }

    @Override
    public BrandResponseDto updateBrand(Integer id, BrandRequestDto brandRequest) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));
        if (brandRequest.getName() == null || brandRequest.getName().isBlank()) {
            throw new BadRequestException("Brand name cannot be empty!");
        }
        brand.setName(brandRequest.getName());
        Brand updated = brandRepository.save(brand);
        return brandMapper.toDto(updated);
    }

    @Override
    public void deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)){
            throw new ResourceNotFoundException("Brand not found with id:" + id);
        }
        brandRepository.deleteById(id);
    }
}
