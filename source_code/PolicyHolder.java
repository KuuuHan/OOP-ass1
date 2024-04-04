package source_code;
/**
 * @ author Han Duc Khang -  s3986602
 */

import java.util.List;

public class PolicyHolder extends Customer{

    public PolicyHolder(String id, String name, InsuranceCard insurancecard, List<Claim> claims) {
        super(id, name, insurancecard, claims);
        this.dependents = dependents;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
}
