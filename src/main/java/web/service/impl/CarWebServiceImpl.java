package web.service.impl;

import entities.Car;
import exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import repository.CarRepository;
import web.service.ICarWebService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarWebServiceImpl implements ICarWebService {
    private final CarRepository carRepository;

    @Autowired
    public CarWebServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    //N+1 PROBLEM
    public List<Car> getAllCars() {
        try {
            List<Car> cars = carRepository.findAllWithBrand(); // custom query'i çağır
            if (cars == null) return List.of();
            return cars;
        } catch (Exception e) {
            throw new BadRequestException("Error fetching cars: " + e.getMessage());
        }
    }
    @Override
    public List<Car> searchCars(String query) {
        List<Car> cars = carRepository.findAll();

        if (query == null || query.isBlank()) {
            return cars;
        }

        // Küçük/büyük harf duyarsız filtreleme (Java tarafında)
        String lowerQuery = query.toLowerCase();

        return cars.stream()
                .filter(car -> car.getModel() != null &&
                        car.getModel().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());

    }
}
