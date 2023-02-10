package com.system.futsal_management_system.Service;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.entity.Booking;

import java.io.IOException;
import java.util.List;

public interface BookingService {
    BookingPojo saveOrder(BookingPojo bookingPojo);
    List<Booking> fetchAll();

}
