package com.system.futsal_management_system.Service.Impl;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.Repo.BookingRepo;
import com.system.futsal_management_system.Repo.FutsalRepo;
import com.system.futsal_management_system.Repo.UserRepo;
import com.system.futsal_management_system.Service.BookingService;
import com.system.futsal_management_system.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final FutsalRepo futsalRepo;

    @Override
    public BookingPojo saveOrder(BookingPojo bookingPojo){
        Booking booking = new Booking();

        booking.setBookId(bookingPojo.getBookId());
        booking.setFutsal(futsalRepo.findById(bookingPojo.getFid()).orElseThrow());
        booking.setUser(userRepo.findById(bookingPojo.getId()).orElseThrow());
        booking.setDate(Date.valueOf(bookingPojo.getDate()));
        booking.setStarting(bookingPojo.getStarting());
        booking.setEnding(bookingPojo.getEnding());
    bookingRepo.save(booking);
    return new BookingPojo(booking);


    }
    @Override
    public List<Booking> fetchAll(){return this.bookingRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        bookingRepo.deleteById(id);

    }

    @Override
    public Booking fetchById(Integer id) {
        return bookingRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldnot find"));

    }


}
