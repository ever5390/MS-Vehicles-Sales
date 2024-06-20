package com.erosales.vehicle_sales.service;

import com.erosales.vehicle_sales.entity.Bike;
import com.erosales.vehicle_sales.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id) {
        
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike Bike) {
        Bike carNew = bikeRepository.save(Bike);
        return carNew;
    }

    public List<Bike> getBikeByUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }
}
