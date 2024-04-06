package Module;
import Module.InsuranceCard;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Han Duc Khang -  s3986602
 */
public abstract class Customer {
    private static String id;
    private String name;
    private final InsuranceCard insurancecard;
    private final List<Claim> claims;

    public Customer(String id, String name, InsuranceCard insurancecard, List<Claim> claims) {
        Customer.id = id;
        this.name = name;
        this.insurancecard = insurancecard;
        this.claims = claims;
    }

    // Getters
    public static String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InsuranceCard getInsurancecard() {
        return insurancecard;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    //setters
    public void setId(String id) {
        Customer.id = id;}

    public void setName(String name) {this.name = name;}

    public boolean customerIdValid(){return Pattern.matches("c-\\d{7}", id);}

    @Override
    public String toString() {
        return id + "," + name + "," + insurancecard + "," + claims;
    }
}
