package com.vinterdo.deusexmachina.network;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Synchronized
{
    // Everything that has this anotation and has Container deriving form
    // ContainerDEM will be server - client synced
	int id();
}
