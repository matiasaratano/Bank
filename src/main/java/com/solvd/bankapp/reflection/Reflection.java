package com.solvd.bankapp.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
            Field[] fields = reflectedClass.getDeclaredFields();
            //Reflection.print("Fields", fields);
            LOGGER.info("List of " + "Fields" + ":");
            for (Field field : fields) {
                LOGGER.info(field.getName() + ": " + field.getType());
            }

            ReflectionAccountExample reflectedResult;
            Class<ReflectionAccountExample> resultClass = (Class<ReflectionAccountExample>) Class.forName(PATH);
            reflectedResult = resultClass.newInstance();
            //Default constructor
            System.out.println("Default Constructor");
            LOGGER.info(reflectedResult.toString());
            // Call a method on the object using reflection
            Method method = reflectedResult.getClass().getMethod("setDefaulter", boolean.class);
            method.invoke(reflectedResult, true);
            //reflectedResult.setDefaulter(true);
            reflectedResult.setBalance(10000);
            reflectedResult.setName("Sergio");
            reflectedResult.setId("2");
            //Constructor with arguments
            System.out.println("Constructor with different arguments");
            LOGGER.info(reflectedResult.toString());

        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }
}