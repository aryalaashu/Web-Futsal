package com.system.futsal_management_system.Repo;

import com.system.futsal_management_system.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact ,Integer> {

}


