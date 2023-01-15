package org.mcmasterkboys.codenamehenryford.objects;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;


@Getter
@Setter
public class User extends DatabaseObject {

    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String uuid;
    private String phoneNumber;
    private String gender;
    private Address address;

    private String tableName = "users";
    private String pkName = "uuid";
    private String pkType = "String";

    public User() {
        super(SQLConnectionFactory.class);
    }

    public User(String lastName, String firstName, String email, String password, String uuid, String phoneNumber, Gender gender, Address address) {
        super(SQLConnectionFactory.class);
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.gender = gender.toString();
        this.address = address;
    }

    public void generateUUID() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }
}
