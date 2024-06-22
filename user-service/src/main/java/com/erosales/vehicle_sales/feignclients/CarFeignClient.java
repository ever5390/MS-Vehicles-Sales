package com.erosales.vehicle_sales.feignclients;


import com.erosales.vehicle_sales.feignclients.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "car-service", url = "${application.config.car-url}")
@FeignClient(name = "car-service", path = "/car")
public interface CarFeignClient {

    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping("/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
