package web.controller.impl;

import controller.ICarController;
import entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.controller.ICarWebController;
import web.service.ICarWebService;
import web.service.impl.CarWebServiceImpl;

import java.util.Comparator;
import java.util.List;

@Controller
public class CarWebControllerImpl implements ICarWebController {
    private final ICarWebService carWebService;

    @Autowired
    public CarWebControllerImpl(CarWebServiceImpl carWebService) {
        this.carWebService = carWebService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Car> cars = carWebService.getAllCars();
        if (cars == null) cars = List.of(); // Boş liste kontrolü
        model.addAttribute("cars", cars);
        return "index"; // templates/index.html render
    }

    @GetMapping("/cars")
    public String getCars(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String sort,
            Model model
    ) {
        // Tüm arabaları çek
        List<Car> cars = carWebService.getAllCars();


        // Filtreleme
        cars = cars.stream()
                .filter(car -> query == null || query.isBlank() || car.getModel().toLowerCase().contains(query.toLowerCase()))
                .filter(car -> brand == null || brand.isBlank() || car.getBrand().getName().equalsIgnoreCase(brand))
                .filter(car -> color == null || color.isBlank() || car.getColor().equalsIgnoreCase(color))
                .filter(car -> minPrice == null || car.getPrice() >= minPrice)
                .filter(car -> maxPrice == null || car.getPrice() <= maxPrice)
                .filter(car -> minYear == null || car.getYear() >= minYear)
                .filter(car -> maxYear == null || car.getYear() <= maxYear)
                .toList();

        // Sıralama
        if ("priceAsc".equals(sort)) cars = cars.stream().sorted(Comparator.comparing(Car::getPrice)).toList();
        else if ("priceDesc".equals(sort)) cars = cars.stream().sorted(Comparator.comparing(Car::getPrice).reversed()).toList();
        else if ("yearAsc".equals(sort)) cars = cars.stream().sorted(Comparator.comparing(Car::getYear)).toList();
        else if ("yearDesc".equals(sort)) cars = cars.stream().sorted(Comparator.comparing(Car::getYear).reversed()).toList();

        // Thymeleaf’e gönder
        model.addAttribute("cars", cars);
        return "cars"; // templates/cars.html
    }

}
