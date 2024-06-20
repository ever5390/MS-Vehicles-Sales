package com.erosales.vehicle_sales.service;

import com.erosales.vehicle_sales.entity.UserSeller;
import com.erosales.vehicle_sales.feignclients.BikeFeignClient;
import com.erosales.vehicle_sales.feignclients.CarFeignClient;
import com.erosales.vehicle_sales.feignclients.model.Bike;
import com.erosales.vehicle_sales.feignclients.model.Car;
import com.erosales.vehicle_sales.repository.UserSellerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSellerService {

    UserSellerRepository userRepository;

    RestTemplate restTemplate;

    CarFeignClient carFeignClient;

    BikeFeignClient bikeFeignClient;


    public UserSellerService(UserSellerRepository userRepository, RestTemplate restTemplate, CarFeignClient carFeignClient, BikeFeignClient bikeFeignClient) {
        this.carFeignClient = carFeignClient;
        this.bikeFeignClient = bikeFeignClient;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public List<UserSeller> getAll() {
        return userRepository.findAll();
    }

    public UserSeller getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserSeller save(UserSeller user) {
        UserSeller userNew = userRepository.save(user);
        return userNew;
    }

    /* ::: Rest Template ::: */
    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }


    /* ::: Feign ::: */
    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        UserSeller user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            result.put("Mensaje", "no existe el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars.isEmpty())
            result.put("Cars", "ese user no tiene coches");
        else
            result.put("Cars", cars);
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty())
            result.put("Bikes", "ese user no tiene motos");
        else
            result.put("Bikes", bikes);
        return result;
    }
}
