package com.trainin.persist.repo;

import com.trainin.persist.model.Car;
import com.trainin.persist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByUser(User user);
    void deleteByUser(User user);
}
