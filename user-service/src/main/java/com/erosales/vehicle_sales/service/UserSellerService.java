package com.erosales.vehicle_sales.service;

import com.erosales.vehicle_sales.entity.UserSeller;
import com.erosales.vehicle_sales.model.Bike;
import com.erosales.vehicle_sales.model.Car;
import com.erosales.vehicle_sales.repository.UserSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserSellerService {

    UserSellerRepository userRepository;

    RestTemplate restTemplate;

    public UserSellerService(UserSellerRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public List<UserSeller> getAll() {
        return userRepository.findAll();
    }

    public UserSeller getUserSellerById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserSeller save(UserSeller user) {
        UserSeller userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }
}
