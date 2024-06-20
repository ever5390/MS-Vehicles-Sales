package com.erosales.vehicle_sales.controller;

import com.erosales.vehicle_sales.entity.UserSeller;
import com.erosales.vehicle_sales.model.Bike;
import com.erosales.vehicle_sales.model.Car;
import com.erosales.vehicle_sales.service.UserSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        UserSeller user = userService.getUserSellerById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<UserSeller> save(@RequestBody UserSeller user) {
        UserSeller userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }


    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") int userId) {
        UserSeller user = userService.getUserSellerById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();

        List<Car> cars = userService.getCars(userId);

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikesByUserId(@PathVariable("userId") int userId) {
        UserSeller user = userService.getUserSellerById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();

        List<Bike> bikes = userService.getBikes(userId);

        return ResponseEntity.ok(bikes);
    }

}
