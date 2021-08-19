package com.giembs.contacts;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Wilson
 * on Thu, 19/08/2021.
 */
@Data
@Builder
public class APIResponseJSON<T> {
    private String message;
    private T data;

    public APIResponseJSON(String message){
        this.message = message;
        data = null;
    }
    public APIResponseJSON(String message, T data){
        this.message = message;
        this.data = data;
    }
}
