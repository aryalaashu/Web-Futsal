package com.system.futsal_management_system.Repo;

import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT * FROM booking where u_id=?1", nativeQuery = true)
    List<Booking> findBookingById(Integer id);

}
