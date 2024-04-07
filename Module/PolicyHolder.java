package Module;
/**
 * @ author Han Duc Khang -  s3986602
 */

import java.util.Set;

public class  PolicyHolder extends Customer{
    private Set<Dependent> dependents;

    public PolicyHolder(String id, String name, InsuranceCard insurancecard, Set<Claim> claims, Set<Dependent> dependents) {
        super(id, name, insurancecard, claims);
        this.dependents = dependents;
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(Set<Dependent> dependents) {
        this.dependents = dependents;
    }

}
