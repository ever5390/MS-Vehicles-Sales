package com.erosales.vehicle_sales.controller;

import com.erosales.vehicle_sales.entity.UserSeller;
import com.erosales.vehicle_sales.feignclients.model.Bike;
import com.erosales.vehicle_sales.feignclients.model.Car;
import com.erosales.vehicle_sales.service.UserSellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserSellerController {

    UserSellerService userService;

    public UserSellerController(UserSellerService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserSeller>> getAll() {
        List<UserSeller> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSeller> getById(@PathVariable("id") int id) {
        UserSeller user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<UserSeller> save(@RequestBody UserSeller user) {
        UserSeller userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    /* ::: Rest Template ::: */
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") int userId) {
        UserSeller user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();

        List<Car> cars = userService.getCars(userId);

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikesByUserId(@PathVariable("userId") int userId) {
        UserSeller user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();

        List<Bike> bikes = userService.getBikes(userId);

        return ResponseEntity.ok(bikes);
    }


    /* ::: Feign ::: */
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) {
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }
}
