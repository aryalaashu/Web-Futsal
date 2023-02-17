package com.system.futsal_management_system.Controller;

import com.system.futsal_management_system.Service.FutsalService;
import com.system.futsal_management_system.Service.UserService;
import com.system.futsal_management_system.entity.Futsal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomepageController {
    private final FutsalService futsalService;
    private final UserService userService;

@GetMapping("/homepage")
public String getAllFutsal(Model model, Principal principal , Authentication authentication ){
    if (authentication!=null){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("Admin")) {
                return "redirect:/admin/dashboard";
            }
        }
    }
    List<Futsal> hfutsal = futsalService.fetchAll();
    model.addAttribute("userdata",userService.findByEmail(principal.getName()));
    model.addAttribute("futsals", hfutsal.stream().map(futsal ->
            Futsal.builder()
                    .fut_salId(futsal.getFut_salId())
                    .futsalname(futsal.getFutsalname())
                    .Description(futsal.getDescription())
                    .imageBase64(getImageBase64(futsal.getFutsalimage()))
                    .build()
    ));
    return "Homepage";
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

