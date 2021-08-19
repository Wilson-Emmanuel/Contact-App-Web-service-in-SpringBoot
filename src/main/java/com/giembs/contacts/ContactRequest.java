package com.giembs.contacts;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Wilson
 * on Thu, 19/08/2021.
 */
@Data
@Builder
public class ContactRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
