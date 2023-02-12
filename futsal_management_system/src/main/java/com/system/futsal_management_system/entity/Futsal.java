package com.system.futsal_management_system.entity;



import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Futsal")

public class Futsal {
    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer fut_salId;

    private String futsalname;

    private String futsalprice;

    private String futsalcontact;

    private String futsallocation;

    private String futsalimage;
    private String futsalimage1;
    private String futsalimage2;

    private String Description;

    @Transient
    private String imageBase64;

    @Transient
    private String image1Base64;


    @Transient
    private String image2Base64;

}
