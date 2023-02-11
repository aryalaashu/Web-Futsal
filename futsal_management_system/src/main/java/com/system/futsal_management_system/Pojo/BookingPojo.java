package com.system.futsal_management_system.Pojo;

import com.system.futsal_management_system.entity.Booking;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingPojo {
    private Integer bookId;
    private Integer fid;
    private Integer id;
    private String date;
    private String starting;



    public BookingPojo(Booking booking) {
        this.bookId= booking.getBookId();
        this.fid= booking.getFutsal().getFut_salId();
        this.id= booking.getUser().getId();
        this.date= String.valueOf(booking.getDate());
        this.starting= booking.getStarting();

    }
}

