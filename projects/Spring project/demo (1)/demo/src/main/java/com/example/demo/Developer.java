package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Developer {
    @Autowired
    Laptop laptop;
    public void run() {
        laptop.code();
        laptop.code1();
    }
}
