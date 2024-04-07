package Module;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Han Duc Khang -  s3986602
 */
public abstract class Customer {
    private String id;
    private String name;
    private InsuranceCard insurancecard;
    private Set<Claim> claims;

    public Customer(String id, String name, InsuranceCard insurancecard, Set<Claim> claims) {
        this.id = id;                                                // format: "C" + 7 digits
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

    public void setInsurancecard(InsuranceCard insurancecard) {
        this.insurancecard = insurancecard;
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
