package com.evolyb;

import java.io.*;
import java.lang.reflect.Field;

public class ObjectHelper {
    public static Object cloneObject(Object inputObj) throws ClassNotFoundException {
        Object resultObj = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(inputObj);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            resultObj = in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultObj;
    }

    public static boolean setValueByFieldName(Object object,
                                              String fieldName,
                                              Object fieldValue) {
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}