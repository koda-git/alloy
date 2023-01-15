package org.mcmasterkboys.codenamehenryford.objects.banking;

import lombok.Getter;
import me.hy.libhycore.CoreSHA;
import me.hy.libhyextended.objects.DatabaseObject;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;

@Getter
public class Transactions extends DatabaseObject {

    private String hashRecord; // PK, Hashed: time, ownerUUID, senderUUID, receiverUUID, amount, currency
    private String ownerUUID;
    private String senderUUID;
    private String receiverUUID;
    private float amount;
    private String currency;
    private String transactionName;
    private long transactionTime;

    private String tableName = "transactions";
    private String pkName = "hashRecord";
    private String pkType = "String";

    public Transactions() {
        super(SQLConnectionFactory.class);
    }

    public Transactions(String ownerUUID, String senderUUID, String receiverUUID, float amount, String currency, String transactionName, long transactionTime) {
        super(SQLConnectionFactory.class);
        this.ownerUUID = ownerUUID;
        this.senderUUID = senderUUID;
        this.receiverUUID = receiverUUID;
        this.amount = amount;
        this.currency = currency;
        this.transactionTime = transactionTime;
        this.transactionName = transactionName;
        this.hashRecord = CoreSHA.hash256(transactionTime + ownerUUID + senderUUID + receiverUUID + amount + currency);
    }
}
