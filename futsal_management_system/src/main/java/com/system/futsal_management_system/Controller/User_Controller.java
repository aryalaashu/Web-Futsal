package com.system.futsal_management_system.Controller;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.Pojo.ContactPojo;
import com.system.futsal_management_system.Pojo.FutsalPojo;
import com.system.futsal_management_system.Pojo.UserPojo;
import com.system.futsal_management_system.Service.BookingService;
import com.system.futsal_management_system.Service.ContactService;
import com.system.futsal_management_system.Service.FutsalService;
import com.system.futsal_management_system.Service.UserService;
import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.Contact;
import com.system.futsal_management_system.entity.Futsal;
import com.system.futsal_management_system.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class User_Controller {
    private final UserService userService;
    private final BookingService bookingService;
    private final FutsalService futsalService;
    private final ContactService contactService;

    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserPojo());

        return "signup";
    }


//    @GetMapping("/booked")
//    public String fetchAllbook(Model model ,Integer id){
//        List<Booking> ed = bookingService.fetchAll();
//        model.addAttribute("books", ed.stream().map(booking ->
//                Booking.builder()
//                        .bookId(booking.getBookId())
//                        .date(booking.getDate())
//                        .starting(booking.getStarting())
//                        .ending(booking.getEnding())
//                        .user(booking.getUser())
//                        .futsal(booking.getFutsal())
//                        .build()
//        ));
//        return "bookedfutsal";
//    }

    @GetMapping("/booked/{id}")
    public String fetchAllbook(@PathVariable("id") Integer id, Model model , Principal principal){
        List<Booking> booking= bookingService.findBookingById(id);
        model.addAttribute("books",booking);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        return "bookedfutsal";
    }


    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home/homepage";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/user/login";
    }

    @GetMapping("/profile")
    public String profile() {

        return "editprofile";
    }

    @GetMapping("/profile/{id}")
    public String getUserProfiile(@PathVariable("id") Integer id, Model model){
        User user = userService.fetchById(id);
        model.addAttribute("users", new UserPojo(user));
        model.addAttribute("currentUser", user);

        return "editprofile";
    }


    @GetMapping("/contactus")
    public String contact(Model model) {
        model.addAttribute("contact", new ContactPojo());
        return "Contactus";
    }


    @PostMapping("/savecontact")
    public String save(@Valid ContactPojo contactPojo) {
        contactService.save(contactPojo);
        return "redirect:/home/homepage";
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

        @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user =userService.fetchById(id);
        model.addAttribute("currentUser", new UserPojo(user));
        return "redirect:/home/homepage";
    }

    @GetMapping("/product/{id}")
    public String getFutsalProfiile(@PathVariable("id") Integer id, Model model, Principal principal ){
        Booking booking = bookingService.fetchById(id);
        List<Futsal> ed = futsalService.fetchAll();

        model.addAttribute("football", new BookingPojo(booking));
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        model.addAttribute("bookedfutsal", booking);
        model.addAttribute("clickedf",ed.stream().map(futsal ->
                Futsal.builder()
                        .fut_salId(futsal.getFut_salId())
                        .futsalcontact(futsal.getFutsalcontact())
                        .futsallocation(futsal.getFutsallocation())
                        .futsalprice(futsal.getFutsalprice())
                        .build()
        ));
        return "editbooking";
    }

    @GetMapping("/editbook/{id}")
    public String editbook(@PathVariable("id") Integer id, Model model){
        Booking booking =bookingService.fetchById(id);
        model.addAttribute("bookedfutsal", new BookingPojo(booking));
        return "redirect:/home/homepage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/user/login";
    }

    @GetMapping("/deletebook/{id}")
    public String deletebooking(@PathVariable("id") Integer id) {
        bookingService.deleteById(id);
        return "redirect:/user/booked";
    }


    @GetMapping("/del/{id}")
    public String deletereview(@PathVariable("id") Integer id) {
        contactService.deleteById(id);
        return "redirect:/admin/dashboard";
    }


    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model){
        model.addAttribute("users",new UserPojo());
        return ("forgetpassword");
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid UserPojo userPojo){
        userService.processPasswordResetRequest(userPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/user/login";
    }
}







