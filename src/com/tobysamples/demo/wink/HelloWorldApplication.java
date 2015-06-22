package com.tobysamples.demo.wink;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class HelloWorldApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HelloWorldResource.class);
        return classes;
    }

}