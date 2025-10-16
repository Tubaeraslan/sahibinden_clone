package web.service.impl;

import entities.Car;
import exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Car> getAllCars() {
        try {
            List<Car> cars = carRepository.findAll();
            if (cars == null) return List.of();
            // Lazy load kontrolü
            cars.forEach(car -> {
                if (car.getBrand() != null) car.getBrand().getName();
            });
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
