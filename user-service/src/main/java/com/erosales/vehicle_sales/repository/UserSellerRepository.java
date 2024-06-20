package com.erosales.vehicle_sales.repository;

import com.erosales.vehicle_sales.entity.UserSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSellerRepository extends JpaRepository<UserSeller, Integer> {
}
