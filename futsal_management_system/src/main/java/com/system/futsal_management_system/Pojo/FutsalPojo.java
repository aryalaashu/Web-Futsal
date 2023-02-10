package com.system.futsal_management_system.Pojo;

import com.system.futsal_management_system.entity.Futsal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FutsalPojo {
    private Integer fid;
    private String fname;
    private String fcontact;
    private  String fprice;
    private String flocation;
    private String fmap;
    private MultipartFile image;
    private String Description;


    public FutsalPojo(Futsal futsal) {
        this.fid = futsal.getFut_salId();
        this.fname = futsal.getFutsalname();
        this.fcontact= futsal.getFutsalcontact();
        this.fprice = futsal.getFutsalprice();
        this.flocation = futsal.getFutsallocation();
        this.fmap = futsal.getFutsalmap();
        this.Description = futsal.getDescription();

    }
}