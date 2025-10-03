package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;  //year integer olunca database de virgüllü gözüktü sonra düzelt

    @Column(name = "price")
    private double price;

    @Column(name = "km")
    private Integer km;

    @Column(name = "color")
    private String color;

    //Brand class ı ile merge le table lar arası ilişki kur
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public Car(){};

    public Car(Integer id, String model, Integer year, double price, Integer km, String color, Brand brand) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
        this.km = km;
        this.color = color;
        this.brand = brand;
    }
}
