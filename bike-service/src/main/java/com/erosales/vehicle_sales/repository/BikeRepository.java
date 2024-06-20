package com.erosales.vehicle_sales.repository;

import com.erosales.vehicle_sales.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> findByUserId(int id);
}
