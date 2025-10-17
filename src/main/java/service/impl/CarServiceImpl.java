package service.impl;

import dto.requestDto.CarRequestDto;
import dto.responseDto.CarResponseDto;
import entities.Brand;
import entities.Car;
import exception.ResourceNotFoundException;
import mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.BrandRepository;
import repository.CarRepository;
import service.ICarService;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarMapper carMapper;


    @Override
    public Page<CarResponseDto> getAllCars(Integer page,Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Car> carPage = carRepository.findAll(pageable);
        return carPage.map(carMapper::toDto);
    }

    @Override
    public CarResponseDto getCarById(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car not found with id:" + id));
        return carMapper.toDto(car);
    }

    //add update method

    @Override
    public CarResponseDto addCar(CarRequestDto carRequest) {
        // Önce brand tut
        Optional<Brand> brandOpt = brandRepository.findById(carRequest.getBrandId());
        if (brandOpt.isEmpty()) {
           throw new RuntimeException("Brand not found with id: " + carRequest.getBrandId());
        }

        Car car = carMapper.toEntity(carRequest);
        car.setBrand(brandOpt.get());

        Car saved = carRepository.save(car);
        return carMapper.toDto(saved);
    }

    @Override
    public CarResponseDto updateCar(Integer id, CarRequestDto carRequest) {
        // 1️⃣ Mevcut araba var mı kontrol et
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));

        // 2️⃣ Brand kontrolü
        Brand brand = brandRepository.findById(carRequest.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + carRequest.getBrandId()));

        // 3️⃣ Alanları güncelle
        existingCar.setModel(carRequest.getModel());
        existingCar.setPrice(carRequest.getPrice());
        existingCar.setYear(carRequest.getYear());
        existingCar.setBrand(brand);

        // 4️⃣ Kaydet ve DTO olarak döndür
        Car updatedCar = carRepository.save(existingCar);
        return carMapper.toDto(updatedCar);
    }

    @Override
    public void deleteCar(Integer id) {
        if (!carRepository.existsById(id)){
            throw new ResourceNotFoundException("Car not found with id:" + id);
        }
        carRepository.deleteById(id);
    }
}
