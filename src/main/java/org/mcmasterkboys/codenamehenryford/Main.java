package org.mcmasterkboys.codenamehenryford;

import com.google.gson.JsonObject;
import org.mcmasterkboys.codenamehenryford.objects.Address;
import org.mcmasterkboys.codenamehenryford.objects.Gender;
import org.mcmasterkboys.codenamehenryford.objects.User;

public class Main {
    public static void main(String[] args) throws Exception {

        Address a = new Address("1234", "Toronto", "ON", "Canada", "M4T 1J8");
        //public User(String lastName, String firstName, String email, String password, String uuid, String phoneNumber, Address address) {
        User u = new User("asdf", "Appleseed", "John", "johnappleseed@apple.com", "password", "1234567890", Gender.MALE, a);

//        u.insert();
        JsonObject j = u.toJson();
        User o = new User();
        o.fromJson(j);

        System.out.println(o);
    }
}
