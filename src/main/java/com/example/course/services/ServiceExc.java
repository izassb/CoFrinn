package com.example.course.services;

import java.io.Serial;

public class ServiceExc extends Exception {

    public ServiceExc(String message){
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
