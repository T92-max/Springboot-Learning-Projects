package com.teju.SpringbootFirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Laptop {

    @Autowired
    Cpu cp;
    public void compile(){
        System.out.println("compiling");
        cp.process();
    }
}
