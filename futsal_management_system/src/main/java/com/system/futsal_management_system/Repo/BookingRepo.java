package com.system.futsal_management_system.Repo;

import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
