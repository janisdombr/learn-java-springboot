package com.trainin.persist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_id_seq",
            sequenceName = "car_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_id_seq"
    )
    private Integer id;

    @Nonnull
    private String name;

    @Nonnull
    private Double price;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Car() {
        name = "";
        price = 0.0;
        user = new User();
    }

    public Car(@Nonnull User user) {
        name = "";
        price = 0.0;
        this.user = user;
    }

    public Car(Integer id, @Nonnull String name, @Nonnull Double price, @Nonnull User user) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public Double getPrice() {
        return price;
    }

    public void setPrice(@Nonnull Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(name, car.name) && Objects.equals(price, car.price) && Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, user);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
