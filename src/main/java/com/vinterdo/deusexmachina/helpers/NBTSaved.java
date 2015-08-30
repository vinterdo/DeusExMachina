package com.vinterdo.deusexmachina.helpers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NBTSaved
{
	String name();
}
