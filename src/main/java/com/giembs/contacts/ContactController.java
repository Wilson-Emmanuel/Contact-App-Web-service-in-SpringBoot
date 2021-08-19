package com.giembs.contacts;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController(value = "api/v1/contacts")
public class ContactController {


    private ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponseJSON<List<ContactResponse>>> getAllContact(){
        List<ContactResponse> contactResponses = contactService.getAllContact();
        return new ResponseEntity<>(new APIResponseJSON<>("Successfully retrieved",contactResponses),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseJSON<ContactResponse>> getContact(@PathVariable("id") Long id){
        ContactResponse contactResponse = contactService.getContact(id);
        return new ResponseEntity<>(new APIResponseJSON<>("Successfully retrieved",contactResponse),HttpStatus.OK);
    }

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<APIResponseJSON<ContactResponse>> saveContact(@RequestBody @Valid ContactJSON contactJSON){
        System.out.println("Accessing the post method");
        ContactResponse contactResponse = contactService.saveContact(contactJSON.getRequest());
        return new ResponseEntity<>(new APIResponseJSON<>("Successfully inserted",contactResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponseJSON<String>> saveContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok(new APIResponseJSON<>("ContactEntity deleted successfully!"));
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponseJSON<ContactResponse>> updateContact(@PathVariable("id") Long id, @RequestBody @Valid ContactJSON contactJSON ){
        ContactResponse contactResponse = contactService.updateContact(id,contactJSON.getRequest());
        return new ResponseEntity<>(new APIResponseJSON<>("Successfully updated",contactResponse),HttpStatus.OK);
    }

}
