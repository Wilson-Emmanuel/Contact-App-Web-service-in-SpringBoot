package com.giembs.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactResponse> getAllContact(){  // A service method to return a query of all the available data stored in the database
        List<ContactEntity> contactEntities = contactRepository.findAll();
        return contactEntities.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public ContactResponse getContact(Long id){
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(()->new ContactNotFoundException("Error: Trying to access a non-existing data"));
        return convertEntityToResponse(contactEntity);
    }

    @Override
    public ContactResponse saveContact(ContactRequest contactRequest){
        ContactEntity contactEntity = ContactEntity.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .email(contactRequest.getEmail())
                .phone(contactRequest.getPhone())
                .date(Instant.now())
                .build();
        contactEntity = contactRepository.save(contactEntity);
        return this.convertEntityToResponse(contactEntity);
    }

    @Override
    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }

    @Override
    public ContactResponse updateContact(Long id, ContactRequest contactRequest){
        ContactEntity updatingContactEntity = contactRepository.findById(id).orElseThrow(()->new ContactNotFoundException("ContactEntity not found"));

        updatingContactEntity.setFirstName(contactRequest.getFirstName());
        updatingContactEntity.setLastName(contactRequest.getLastName());
        updatingContactEntity.setPhone(contactRequest.getPhone());
        updatingContactEntity.setEmail(contactRequest.getEmail());

        updatingContactEntity = contactRepository.save(updatingContactEntity);
        return this.convertEntityToResponse(updatingContactEntity);
    }

    public ContactResponse convertEntityToResponse(ContactEntity contactEntity){
        return ContactResponse.builder()
                .id(contactEntity.getId())
                .firstName(contactEntity.getFirstName())
                .lastName(contactEntity.getLastName())
                .email(contactEntity.getEmail())
                .phone(contactEntity.getPhone())
                .date(LocalDateTime.ofInstant(contactEntity.getDate(),ZoneId.of("Africa/Lagos")).format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
