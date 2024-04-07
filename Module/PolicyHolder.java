package Module;
/**
 * @ author Han Duc Khang -  s3986602
 */

import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class  PolicyHolder extends Customer{
    private List<Dependent> dependents;

    public PolicyHolder(String id, String name, String insuranceCardID, List<String> claimID, List<Dependent> dependents) {
        super(id, name, insuranceCardID, claimID);
        this.dependents = dependents;
    }
    public List<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

}
