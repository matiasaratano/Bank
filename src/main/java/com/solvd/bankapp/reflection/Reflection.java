package com.solvd.bankapp.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {
    private static final Logger LOGGER = LogManager.getLogger(Reflection.class);

    public static void print(String types, Object[] names) {
        LOGGER.info("List of " + types + ":");
        for (Object o : names) {
            LOGGER.info(o.toString());
        }
    }

    public static void main(String[] args) {
        final String PATH = "com.solvd.bankapp.reflection.ReflectionAccountExample";
        try {
            //Reflected class
            Class reflectedClass = Class.forName(PATH);
            //Constructors
            Constructor[] constructors = reflectedClass.getConstructors();
            Reflection.print("Constructors", constructors);
            //Methods
            Method[] methods = reflectedClass.getMethods();
            Reflection.print("Methods", methods);
            //Fields
            Field[] fields = reflectedClass.getFields();
            Reflection.print("Fields", fields);

            ReflectionAccountExample reflectedResult = null;
            Class<ReflectionAccountExample> resultClass = (Class<ReflectionAccountExample>) Class.forName(PATH);
            reflectedResult = resultClass.newInstance();
            //Default constructor
            LOGGER.info(reflectedResult.toString());
            reflectedResult.setDefaulter(true);
            reflectedResult.setBalance(10000);
            reflectedResult.setName("Sergio");
            reflectedResult.setId("2");
            //Constructor with arguments
            LOGGER.info(reflectedResult.toString());

        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}