package com.system.futsal_management_system;

import com.system.futsal_management_system.Repo.FutsalRepo;
import com.system.futsal_management_system.Repo.UserRepo;
import com.system.futsal_management_system.entity.Contact;
import com.system.futsal_management_system.entity.Futsal;
import com.system.futsal_management_system.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FutsalRepositoryTest {
    @Autowired
    private FutsalRepo futsalRepo;


    @Test
    @Order(1)
    public void savefutsalTest() {

        Futsal futsal = Futsal.builder()
                .futsalname("rak")
                .futsalcontact("123454")
                .futsalprice("98888888")
                .build();


        futsalRepo.save(futsal);

        Assertions.assertThat(futsal.getFut_salId()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updatefutsalTest(){

        Futsal futsal = Futsal.builder()
                .futsalname("rak")
                .futsalcontact("123454")
                .futsalprice("98888888")
                .build();


        futsalRepo.save(futsal);

        Futsal futsal1  = futsalRepo.findById(futsal.getFut_salId()).get();

        futsal1.setFutsalcontact("13265");

        Futsal futsalupdated  = futsalRepo.save(futsal);

        Assertions.assertThat(futsalupdated.getFutsalcontact()).isEqualTo("85207410");

    }
}