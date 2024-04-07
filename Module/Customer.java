package Module;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a Bank in the Claim Management System.
 * It contains all the details of a bank, including the bank name, account holder name, and account number.
 * @author Han Duc Khang - s3986602
 */

public abstract class Customer {
    private String id;
    private String name;
    private String insuranceCardID;
    private InsuranceCard insurancecard;
    private List<Claim> claims;
    private List<String> claimID;
    private Bank bank;


    public Customer(String id, String name, String insuranceCardID, List<String> claimID) {
        this.id = id;                                                // format: "C" + 7 digits
        this.name = name;
        this.insuranceCardID = insuranceCardID;
        this.claims = new ArrayList<>();
        this.claimID = claimID;
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

    public void setInsuranceCard(InsuranceCard insurancecard) {
        this.insurancecard = insurancecard;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void addCustomerClaim(Claim claim) {this.claims.add(claim);}

    @Override
    public String toString() {
        return id + "," + name + "," + insurancecard + "," + claims;
    }

    public void setBank(Bank bank){
    this.bank = bank;
    }

    public CharSequence getInsuranceID() {
        return insuranceCardID;
    }
}
