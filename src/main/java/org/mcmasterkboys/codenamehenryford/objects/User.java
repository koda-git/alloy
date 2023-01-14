package org.mcmasterkboys.codenamehenryford.objects;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
public class User extends DatabaseObject {

    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String uuid;
    private String phoneNumber;
    private Address address;


    public User() {
        super();
        this.setPkName("uuid");
        this.setPkType("String");
    }

    public User(String uuid) {
        super();
        this.setPkName("uuid");
        this.setPkType("String");
        this.setPkValue(uuid);
    }

    public User(String lastName, String firstName, String email, String password, String uuid, String phoneNumber, Address address) {
        super();
        this.setPkName("uuid");
        this.setPkType("String");
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public void insert() throws SQLException {

    }

    @Override
    public void delete() throws SQLException {

    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public void select() throws SQLException {

    }
}
