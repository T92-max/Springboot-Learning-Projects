package com.teju.SpringbootFirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Simple {



    @Autowired
    Laptop laptop;

    public void code(){
        System.out.println("coding");
        laptop.compile();
    }

   /* public void implement(){
        System.out.println("implementing");
    }*/

}
