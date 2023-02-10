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

    private String futsalmap;

    private String futsalimage;

    private String Description;

    @Transient
    private String imageBase64;

}
