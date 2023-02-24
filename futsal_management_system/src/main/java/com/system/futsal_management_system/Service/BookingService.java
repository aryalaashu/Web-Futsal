package com.system.futsal_management_system.Service;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.entity.Booking;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingPojo saveOrder(BookingPojo bookingPojo);
    List<Booking> fetchAll();

    void deleteById(Integer id);

    Booking fetchById(Integer id);

    List findBookingById(Integer id);
    List<String> bookedTime(Date date, Integer id);


    void processPasswordResetRequest(String email);

    void sendEmail();
}
