package com.ted.snider.converter.mapping;

import java.lang.reflect.InvocationTargetException;

public interface Mapping {
    public String evaluate(String[] arguments)  throws InvocationTargetException;
}
