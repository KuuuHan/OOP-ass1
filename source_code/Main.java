package source_code;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String expectedClaimID = "f-1234567890";
        Claim claim = new Claim(expectedClaimID, new Date(), new Date(), 1000.0, "Pending", "John Doe", null, null, new ArrayList<>(), null);
        String actualClaimID = claim.getClaimID();

        if (expectedClaimID.equals(actualClaimID)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed: Expected " + expectedClaimID + " but got " + actualClaimID);
        }
    }
}
