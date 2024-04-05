package source_code;

/**
 * @author Han Duc Khang -  s3986602
 */

import java.util.List;
public class Dependent extends Customer {
    public Dependent(String id, String name, InsuranceCard insurancecard, List<Claim> claims) {
        super(id, name, insurancecard, claims);
    }
}
