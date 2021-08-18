package com.giembs.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    private HttpHeaders headers;

    @Autowired
    private ContactService contactService;

    @GetMapping("api/v1/contacts")
    public ResponseEntity<Contact> getAllContact(){
        headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Content-Type", "application/json");
        return new ResponseEntity(contactService.getAllContact(), headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/contacts/{id}")
    public Contact getContact(@PathVariable("id") Long id){
        return contactService.getContact(id);
    }

    @PostMapping("api/v1/contacts")
    public  ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Content-Type", "application/json");
        System.out.println("Accessing the post method");
        return new ResponseEntity(contactService.saveContact(contact),headers, HttpStatus.OK);
    }

    @DeleteMapping("api/v1/contacts/{id}")
    public String saveContact(@PathVariable("id") Long id){
        return contactService.deleteContact(id);
    }

    @PutMapping("api/v1/contacts/{id}")
    public  Contact updateContact(@PathVariable("id") Long id, @RequestBody Contact contact){
        return contactService.updateContact(id, contact);
    }

}
