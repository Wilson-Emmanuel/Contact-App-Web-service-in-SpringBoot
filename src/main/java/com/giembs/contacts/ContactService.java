package com.giembs.contacts;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContact(){  // A service method to return a query of all the available data stored in the database
        return contactRepository.findAll();
    }

    @SneakyThrows
    public Contact getContact(Long id){
        //Implement the Error handling here
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if(!optionalContact.isPresent()){
            throw new ContactNotFoundException("Error: Trying to access a non-existing data");
        }
        return optionalContact.get();
    }

    public  Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }

    public String deleteContact(Long id){
        contactRepository.deleteById(id);
        return "Contact deleted successfully!";
    }

    public Contact updateContact(Long id, Contact contact){
        Contact updatingContact = contactRepository.findById(id).get();
        //An update on the first name field only if set... same down
        if(Objects.nonNull(contact.getFirstName()) && !"".equalsIgnoreCase(contact.getFirstName())){
            updatingContact.setFirstName(contact.getFirstName());
        }

        if(Objects.nonNull(contact.getLastName()) && !"".equalsIgnoreCase(contact.getLastName())){
            updatingContact.setLastName(contact.getLastName());
        }

        if(Objects.nonNull(contact.getEmail()) && !"".equalsIgnoreCase(contact.getEmail())){
            updatingContact.setEmail(contact.getEmail());
        }

        if(Objects.nonNull(contact.getPhone()) && !"".equalsIgnoreCase(contact.getPhone())){
            updatingContact.setPhone(contact.getPhone());
        }
        return contactRepository.save(updatingContact);


    }
}
