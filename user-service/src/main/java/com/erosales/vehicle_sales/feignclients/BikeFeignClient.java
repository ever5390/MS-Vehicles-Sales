package com.erosales.vehicle_sales.feignclients;

import com.erosales.vehicle_sales.feignclients.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @FeignClient(name = "bike-service", url = "http://localhost:8003/bike")
@FeignClient(name = "bike-service", path = "/bike")
public interface BikeFeignClient {

    @PostMapping()
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
