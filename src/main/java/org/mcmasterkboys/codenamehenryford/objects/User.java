package org.mcmasterkboys.codenamehenryford.objects;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhycore.CoreSHA;
import me.hy.libhyextended.mail.HEMail;
import me.hy.libhyextended.mail.servers.HEGmail;
import me.hy.libhyextended.objects.DatabaseObject;
import me.hy.libhyextended.objects.exception.DataFieldMismatchException;
import me.hy.libhyextended.objects.exception.UndefinedSQLKeyException;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

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
    private String gender;
    private String verificationCode;
    private boolean verified;
    private Address address;

    private String tableName = "users";
    private String pkName = "uuid";
    private String pkType = "String";

    public User() {
        super(SQLConnectionFactory.class);
    }

    public User(String uuid, String lastName, String firstName, String email, String password, String phoneNumber, String gender, String verificationCode, boolean verified, Address address) {
        super(SQLConnectionFactory.class);
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.verificationCode = verificationCode;
        this.verified = verified;
        this.address = address;
    }

    public void loadUsingEmail(String email) throws SQLException, DataFieldMismatchException {
        String origPkName = pkName;
        String origPkType = pkType;
        String origPkVal = this.getPkValue();

        pkName = "email";
        pkType = "String";
        this.setPkValue(email);
        this.select();

        this.pkName = origPkName;
        this.pkType = origPkType;
        this.setPkValue(origPkVal);
    }

    public String hashPassword(String password) {
        return CoreSHA.hash512(password, "cc519fa2f4a333a8b4ce6021041fd3b5057203d9fa057aa3e9a263cc4cb9d8b678b58180890bc181a6bed1fa34d5b593fd2dd15cb37dff17ed3370958846f158");
    }

    public void sendVerificationCode() {
        // TODO: Send verification code to user's email
    }
}
