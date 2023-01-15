package org.mcmasterkboys.codenamehenryford.objects;

import com.google.gson.JsonObject;
import org.mcmasterkboys.codenamehenryford.objects.exception.DataFieldMismatchException;

import java.lang.reflect.Field;

public class DataObject {

    public JsonObject toJson() {
        Class<?> reflectedClass = this.getClass();

        Field[] declaredFields = reflectedClass.getDeclaredFields();

        JsonObject json = new JsonObject();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                // Get type
                Class<?> type = field.getType();
                String typeName = type.getName().toLowerCase();

                if (typeName.contains("int"))             json.addProperty(field.getName(), Integer.parseInt(field.get(this).toString()));
                else if (typeName.contains("float"))      json.addProperty(field.getName(), Float.parseFloat(field.get(this).toString()));
                else if (typeName.contains("double"))     json.addProperty(field.getName(), Double.parseDouble(field.get(this).toString()));
                else if (typeName.contains("long"))       json.addProperty(field.getName(), Long.parseLong(field.get(this).toString()));
                else if (typeName.contains("bool"))       json.addProperty(field.getName(), Boolean.parseBoolean(field.get(this).toString()));
                else if (type.getSuperclass().getName().equals(DataObject.class.getName())) json.add(field.getName(), ((DataObject) field.get(this)).toJson());
                else                                      json.addProperty(field.getName(), field.get(this).toString());

            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to export " + field.getName() + " to Json.");
            }
        }

        return json;
    }

    public String toString() {
        return toJson().toString();
    }

    public void fromJson(JsonObject o, Class<?> ... maps) throws DataFieldMismatchException {
        Class<?> reflectedClass = this.getClass();

        Field[] declaredFields = reflectedClass.getDeclaredFields();

        int mapTicks = 0;

        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {

                // Check if it has name
                String name = field.getName();
                if (!o.has(name)) throw new DataFieldMismatchException(DataFieldMismatchException.FIELD_TYPE_MISMATCH, name, null);

                // Get type
                Class<?> type = field.getType();
                String typeName = type.getName().toLowerCase();

                if (typeName.contains("int")) field.set(this, o.get(name).getAsInt());
                else if (typeName.contains("float")) field.set(this, o.get(name).getAsFloat());
                else if (typeName.contains("double")) field.set(this, o.get(name).getAsDouble());
                else if (typeName.contains("long")) field.set(this, o.get(name).getAsLong());
                else if (typeName.contains("bool")) field.set(this, o.get(name).getAsBoolean());
                else if (type.getSuperclass().getName().equals(DataObject.class.getName())) {
                    JsonObject object = o.get(name).getAsJsonObject();
                    DataObject dataObject = (DataObject) type.getDeclaredConstructor().newInstance();
                    dataObject.fromJson(object, maps);
                    field.set(this, dataObject);
                }
                else if (typeName.contains("string")) field.set(this, o.get(name).getAsString());
                else System.out.println("Unknown type: " + typeName + " with type " + type.isAssignableFrom(DataObject.class));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to import " + field.getName() + " from Json.");
                throw new DataFieldMismatchException(e);
            }
        }
    }
}
