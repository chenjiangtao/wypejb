package com.foshanshop.ejb3.impl;

import com.foshanshop.ejb3.HelloWorld;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote ({HelloWorld.class})
public class HelloWorldBean implements HelloWorld {
    
    public String SayHello(String name) {        
        return name +"说：你好!世界,这是我的第一个EJB3哦.";
    }
}
