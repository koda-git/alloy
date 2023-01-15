package org.mcmasterkboys.codenamehenryford.objects;

import lombok.Getter;
import lombok.Setter;
import me.hy.libhyextended.objects.DataObject;

@Getter
@Setter
public class Address extends DataObject {
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;

    public Address(){
        this("", "", "", "", "");
    }
    public Address(String street, String city, String province, String country, String postalCode) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }

}
