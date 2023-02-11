package com.system.futsal_management_system.Service.Impl;

import com.system.futsal_management_system.Pojo.ContactPojo;
import com.system.futsal_management_system.Repo.ContactRepo;
import com.system.futsal_management_system.Service.ContactService;
import com.system.futsal_management_system.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;

    @Override
    public String save(ContactPojo contactPojo){
        Contact contact =new Contact();
        if(contactPojo.getContactId()!=null){
            contact.setContactId(contact.getContactId());
        }
        contact.setContactname(contactPojo.getContactname());
        contact.setContactemail(contactPojo.getContactemail());
        contact.setContactsubject(contactPojo.getContactsubject());
        contact.setContactmessage(contact.getContactmessage());


        contactRepo.save(contact);
        return "created";
    }

}
