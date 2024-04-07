package Module;

/**
 * @author Han Duc Khang -  s3986602
 */

import java.util.Set;
public class Dependent extends Customer {

    public Dependent(String id, String name, InsuranceCard insuranceCard, Set<Claim> claims) {
        super(id, name, insuranceCard, claims);
    }
}
