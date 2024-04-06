package source_code;
/**
 * @Author Han Duc Khang - s3986602
 */

import java.util.List;

public class SystemView {
    public void display(Claim claim) {
        if (claim != null){
            System.out.println("WELCOME TO CLAIM MANAGEMENT SYSTEM");
            System.out.println("-----------------------------------");
            System.out.println("Please choose the following options:");
            System.out.println("1. Add a new claim");
            System.out.println("2. Update a claim");
            System.out.println("3. Delete a claim");
            System.out.println("4. Display all claims");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
        }
    }

    public void displayAll(List<Claim> all) {
    }
}
