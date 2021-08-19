package com.giembs;

import com.giembs.contacts.ContactEntity;
import com.giembs.contacts.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.Instant;

@SpringBootApplication
public class ContactsApplication {

	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@PostConstruct
	public void update(){
		ContactEntity contactEntity = ContactEntity.builder()
				.firstName("Wilson")
				.lastName("Emmanuel")
				.phone("0e847475757")
				.email("wilson@gmail.com")
				.date(Instant.now())
				.build();
		contactRepository.save(contactEntity);
	}
}
