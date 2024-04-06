package source_code;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Han Duc Khang -  s3986602
 */
public abstract class Customer {
    private String id;
    private String name;
    private InsuranceCard insurancecard;
    private List<Claim> claims;

    public Customer(String id, String name, InsuranceCard insurancecard, List<Claim> claims) {
        this.id = id;
        this.name = name;
        this.insurancecard = insurancecard;
        this.claims = claims;
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

    public List<Claim> getClaims() {
        return claims;
    }

    //setters
    public void setId(String id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public boolean customerIdValid(){return Pattern.matches("c-\\d{7}", id);}


}
