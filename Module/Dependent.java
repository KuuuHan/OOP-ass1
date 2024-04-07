package Module;

/**
 * @author Han Duc Khang -  s3986602
 */

import java.util.List;

public class Dependent extends Customer {

    public Dependent(String id, String name, String insuranceCard, List<String> claims) {
        super(id, name, insuranceCard, claims);
    }
}
