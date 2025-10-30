package repository;

import entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    // N+1 probleminden kaçınmak için JOIN FETCH kullanıyoruz
    @Query("SELECT c FROM Car c JOIN FETCH c.brand")
    List<Car> findAllWithBrand();
}