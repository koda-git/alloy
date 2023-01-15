package org.mcmasterkboys.codenamehenryford.objects.banking;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

@Getter
@Setter
public class BankAccount extends DatabaseObject {

    private String uuid;
    private String ownerUUID;
    private String accountNumber;
    private String routingNumber;
    private String bankName;
    private String type;
    private String name;
    private float balance;
    private String currency;

    private String tableName = "bank_accounts";
    private String pkName = "uuid";
    private String pkType = "String";
    public BankAccount() {
        super(SQLConnectionFactory.class);
    }

    public BankAccount(String uuid, String ownerUUID, String accountNumber, String routingNumber, String bankName, String type, String name, float balance, String currency) {
        super(SQLConnectionFactory.class);
        this.uuid = uuid;
        this.ownerUUID = ownerUUID;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.bankName = bankName;
        this.type = type;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }
}
