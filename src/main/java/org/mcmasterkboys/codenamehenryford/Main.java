package org.mcmasterkboys.codenamehenryford;

import org.mcmasterkboys.codenamehenryford.objects.Address;
import org.mcmasterkboys.codenamehenryford.objects.User;
import org.mcmasterkboys.codenamehenryford.objects.exception.DataFieldMismatchException;

public class Main {
    public static void main(String[] args) throws DataFieldMismatchException {

        Address a = new Address("1234", "Toronto", "ON", "Canada", "M4T 1J8");
        //public User(String lastName, String firstName, String email, String password, String uuid, String phoneNumber, Address address) {
        User u = new User("Appleseed", "John", "johnappleseed@apple.com", "password", "1234", "1234567890", a);

        System.out.println(u.toJson());

        System.out.println("!!!!!!!!Conversion done!!!!!!!!!!!");

        User o = new User();
        o.fromJson(u.toJson(), Address.class);

        System.out.println("!!!!!!!!Parsing done!!!!!!!!!!!");

        System.out.println(o.toJson());

    }
}
