package com.system.futsal_management_system.Service.Impl;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.Repo.BookingRepo;
import com.system.futsal_management_system.Repo.FutsalRepo;
import com.system.futsal_management_system.Repo.UserRepo;
import com.system.futsal_management_system.Service.BookingService;
import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final FutsalRepo futsalRepo;

    @Override
    public BookingPojo saveOrder(BookingPojo bookingPojo) {
        Booking booking = new Booking();

        booking.setBookId(bookingPojo.getBookId());
        booking.setFutsal(futsalRepo.findById(bookingPojo.getFid()).orElseThrow());
        booking.setUser(userRepo.findById(bookingPojo.getId()).orElseThrow());
        booking.setDate(Date.valueOf(bookingPojo.getDate()));
        booking.setStarting(bookingPojo.getStarting());
        bookingRepo.save(booking);
        return new BookingPojo(booking);


    }

    public List<Booking> findAllInList(List<Booking> list) {
        Stream<Booking> allBooking = list.stream().map(booking ->
                Booking.builder()
                        .bookId(booking.getBookId())
                        .date(booking.getDate())
                        .starting(booking.getStarting())
                        .user(booking.getUser())
                        .futsal(booking.getFutsal())
                        .build());
        list = allBooking.toList();
        return list;
    }

    @Override
    public List<Booking> fetchAll() {
        return this.bookingRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        bookingRepo.deleteById(id);

    }

    @Override
    public Booking fetchById(Integer id) {
        return bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Couldnot find"));

    }

    @Override
    public List<Booking> findBookingById(Integer id) {
        return findAllInList(bookingRepo.findBookingById(id));
    }


}