package com.giembs.contacts;

import java.util.List;

/**
 * Created by Wilson
 * on Thu, 19/08/2021.
 */
public interface ContactService {
    List<ContactResponse> getAllContact();
    ContactResponse getContact(Long id);
    ContactResponse saveContact(ContactRequest contactRequest);
    void deleteContact(Long id);
    ContactResponse updateContact(Long id, ContactRequest contactRequest);
}
