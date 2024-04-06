package Module;
/**
 * @ author Han Duc Khang -  s3986602
 */

import java.util.List;

public class  PolicyHolder extends Customer{
    private List<Dependent> dependents;

    public PolicyHolder(String id, String name, InsuranceCard insurancecard, List<Claim> claims) {
        super(id, name, insurancecard, claims);
    }

    public List<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

}
