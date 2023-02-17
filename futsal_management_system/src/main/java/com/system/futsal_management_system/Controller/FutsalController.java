package com.system.futsal_management_system.Controller;

import com.system.futsal_management_system.Pojo.BookingPojo;
import com.system.futsal_management_system.Pojo.FutsalPojo;
import com.system.futsal_management_system.Service.BookingService;
import com.system.futsal_management_system.Service.FutsalService;
import com.system.futsal_management_system.Service.UserService;
import com.system.futsal_management_system.entity.Booking;
import com.system.futsal_management_system.entity.Futsal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ball")
public class FutsalController {
    private final FutsalService futsalService;
    private final UserService userService;
    private final BookingService bookingService;
//    @GetMapping("/product")
//    public String product() {
//
//        return "bookfutsal";
//    }

    @GetMapping("/product/{id}")
    public String getFutsalProfiile(@PathVariable("id") Integer id, Model model, Principal principal){
        Futsal futsal = futsalService.fetchById(id);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        model.addAttribute("savebooking", new BookingPojo() );

        model.addAttribute("futsals", new FutsalPojo(futsal));
//
        model.addAttribute("clickedfutsal", futsal);
        return "bookfutsal";
    }

    @PostMapping("/sbooking")
    public String savebooking(@Valid BookingPojo bookingPojo){
        bookingService.saveOrder(bookingPojo);
        return "redirect:/home/homepage";
    }

    @GetMapping("/addfutsal")
    public String createFutsal(Model model) {
        model.addAttribute("futsal", new FutsalPojo());

        return "futsal";
    }
    @PostMapping("/save")
    public String saveFutsal(@Valid FutsalPojo futsalPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException{
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addfutsal";
        }
        futsalService.savefutsal(futsalPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/dashboard";
    }

    private Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
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












}
