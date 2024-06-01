package com.trainin.controller;

import com.trainin.persist.model.Car;
import com.trainin.persist.repo.CarRepository;
import com.trainin.persist.model.User;
import com.trainin.persist.repo.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userid}/cars")
public class CarController {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarController(@Nonnull CarRepository carRepository, @Nonnull UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    // get all cars of current user
    @GetMapping
    public List<Car> getCars(@PathVariable Integer userid) {
        final User user = userRepository.findById(userid).orElseThrow();
        return carRepository.findByUser(user);
    }

    record NewCarRequest(
            String name,
            Double price
    ) {}

    @PostMapping
    public void addCar(@RequestBody NewCarRequest newCarRequest, @PathVariable Integer userid) {
        final User user = userRepository.findById(userid).orElseThrow();
        Car car = new Car(user);
        car.setName(newCarRequest.name());
        car.setPrice(newCarRequest.price());
        carRepository.save(car);
        System.out.print(newCarRequest.toString());
    }
    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable Integer id) {
        carRepository.deleteById(id);
    }
    @DeleteMapping("all")
    public void deleteAll(@PathVariable Integer userid) {
        final User user = userRepository.findById(userid).orElseThrow();
        final List<Car> cars = carRepository.findByUser(user);
        for (Car car: cars) {
            carRepository.deleteById(car.getId());
        }
    }
}
