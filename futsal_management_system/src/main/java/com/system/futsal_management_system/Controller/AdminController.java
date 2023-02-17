package com.system.futsal_management_system.Controller;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.Pojo.FutsalPojo;
import com.system.futsal_management_system.Pojo.UserPojo;
import com.system.futsal_management_system.Service.BookingService;
import com.system.futsal_management_system.Service.ContactService;
import com.system.futsal_management_system.Service.FutsalService;
import com.system.futsal_management_system.Service.UserService;
import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.Contact;
import com.system.futsal_management_system.entity.Futsal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final FutsalService futsalService;
    private final BookingService bookingService;
    private final ContactService contactService;

    @GetMapping("/dashboard")
    public String fetchAllbooking(Model model){
        List<Booking> adminbooking = bookingService.fetchAll();
        model.addAttribute("book", adminbooking.stream().map(booking ->
                Booking.builder()
                        .bookId(booking.getBookId())
                        .date(booking.getDate())
                        .starting(booking.getStarting())
                        .user(booking.getUser())
                        .futsal(booking.getFutsal())
                        .build()
        ));



        return "dashboard";
    }

    @GetMapping("/contact")
    public String createcontact(Model model) {
        List<Contact> admincontact = contactService.fetchAll();
        model.addAttribute("contact", admincontact.stream().map(contact ->
                Contact.builder()
                        .contactId(contact.getContactId())
                        .contactname(contact.getContactname())
                        .contactemail(contact.getContactemail())
                        .contactsubject(contact.getContactsubject())
                        .contactmessage(contact.getContactmessage())
                        .build()
        ));
        return "viewreview";
    }

    @GetMapping("/view")
    public String fetchAllFutsal(Model model){
        List<Futsal> adminfutsal = futsalService.fetchAll();
        model.addAttribute("futsals", adminfutsal.stream().map(futsal ->
                Futsal.builder()
                        .fut_salId(futsal.getFut_salId())
                        .futsalname(futsal.getFutsalname())
                        .futsalprice(futsal.getFutsalprice())
                        .futsalcontact(futsal.getFutsalcontact())
                        .futsallocation(futsal.getFutsallocation())
                        .Description(futsal.getDescription())
                        .imageBase64(getImageBase64(futsal.getFutsalimage()))
                        .image1Base64(getImageBase64(futsal.getFutsalimage1()))
                        .image2Base64(getImageBase64(futsal.getFutsalimage2()))
                        .build()
        ));
        return "viewfutsal";
    }


    @GetMapping("/del/{id}")
    public String deletereview(@PathVariable("id") Integer id) {
        contactService.deleteById(id);
        return "redirect:/admin/dashboard";
    }


    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

//    @GetMapping("/review")
//    public String review() {
//
//        return "viewreview";
//    }
    @GetMapping("/report")
    public String report() {

        return "report";
    }

    @GetMapping("/product/{id}")
    public String getFutsalProfiile(@PathVariable("id") Integer id, Model model ){
        Futsal futsal = futsalService.fetchById(id);
        model.addAttribute("futsals", new FutsalPojo(futsal));

        model.addAttribute("clickedfutsal", futsal);
        return "editfutsal";
    }
    @GetMapping("/edit/{id}")
    public String editfutsal(@PathVariable("id") Integer id, Model model){
        Futsal futsal =futsalService.fetchById(id);
        model.addAttribute("clickedfutsal", new FutsalPojo(futsal));
        return "redirect:/admin/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        futsalService.deleteById(id);
        return "redirect:/admin/view";
    }






}