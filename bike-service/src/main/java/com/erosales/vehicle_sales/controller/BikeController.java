package com.erosales.vehicle_sales.controller;

import com.erosales.vehicle_sales.entity.Bike;
import com.erosales.vehicle_sales.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> users = bikeService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
        Bike user = bikeService.getBikeById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike user) {
        Bike userNew = bikeService.save(user);
        return ResponseEntity.ok(userNew);
    }


    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.getBikeByUserId(userId);
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

}
