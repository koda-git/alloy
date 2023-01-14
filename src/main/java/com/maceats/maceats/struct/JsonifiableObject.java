package com.maceats.maceats.struct;

import com.google.gson.JsonObject;

import java.lang.reflect.Field;

public class JsonifiableObject {
    public JsonObject toJson(Object o) {
        Class<?> reflectedClass = o.getClass();

        Field[] declaredFields = reflectedClass.getDeclaredFields();

        JsonObject json = new JsonObject();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            try {

                // Get type
                Class<?> type = field.getType();
                String typeName = type.getName().toLowerCase();

                if (typeName.contains("int"))         json.addProperty(field.getName(), Integer.parseInt(field.get(o).toString()));
                else if (typeName.contains("float"))  json.addProperty(field.getName(), Float.parseFloat(field.get(o).toString()));
                else if (typeName.contains("double")) json.addProperty(field.getName(), Double.parseDouble(field.get(o).toString()));
                else if (typeName.contains("long"))   json.addProperty(field.getName(), Long.parseLong(field.get(o).toString()));
                else if (typeName.contains("bool"))   json.addProperty(field.getName(), Boolean.parseBoolean(field.get(o).toString()));
                else                                  json.addProperty(field.getName(), field.get(o).toString());



            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to export " + field.getName() + " to Json.");
            }
        }

        return json;
    }
}
