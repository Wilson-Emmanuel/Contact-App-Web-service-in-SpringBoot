package com.giembs.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * Created by Wilson
 * on Thu, 19/08/2021.
 */
@Data
public class ContactJSON {

    @JsonProperty(value = "first_name")
    @NotNull
    @NotEmpty
    private String firstName;

    @JsonProperty(value = "last_name")
    @NotNull
    @NotEmpty
    private String lastName;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^+?[0-9]{11,15}$")
    private String phone;

    public ContactRequest getRequest(){
        return ContactRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .build();
    }
}
