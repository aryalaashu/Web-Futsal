package com.system.futsal_management_system.Repo;

import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT * FROM booking where u_id=?1", nativeQuery = true)
    List<Booking> findBookingById(Integer id);

    @Query(value = "select starting from booking where date=?1 and f_id=?2", nativeQuery = true)
    List<String> selectedTimes(Date date, Integer id);

}
