package Module;
import Module.InsuranceCard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Han Duc Khang -  s3986602
 */
public abstract class Customer {
    private String id;
    private String name;
    private InsuranceCard insurancecard;
    private Set<Claim> claims;

    public Customer(String id, String name, InsuranceCard insurancecard, Set<Claim> claims) {
        this.id = id;
        this.name = name;
        this.insurancecard = insurancecard;
        this.claims = claims != null ? claims : new HashSet<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InsuranceCard getInsurancecard() {
        return insurancecard;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public void addClaim(Claim claim) {claims.add(claim);}

    @Override
    public String toString() {
        return id + "," + name + "," + insurancecard + "," + claims;
    }
}
