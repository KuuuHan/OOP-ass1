package Controller;

import Module.Claim;
import Module.ClaimProcess;
import Module.ClaimProcessManager;
import Module.Customer;
import View.SystemView;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author Han Duc Khang - s3986602
 */
public class ClaimController {
    private ClaimProcess claimInterface;
    private ClaimProcessManager claimProcessManager;
    private SystemView systemView;
    private List<Customer> customers;

    public ClaimController(ClaimProcess claimInterface, SystemView systemView, List<Customer> customers) {
        this.claimInterface = claimInterface;
        this.systemView = systemView;
        this.customers = customers;
    }
    public void setSystemView(SystemView systemView) {this.systemView = systemView;}
    public void addClaim(Claim claim) {
        claimInterface.add(claim);
        FileHandler.writeToFileClaim(claim);
    }

    public Customer getCustomerById(String id) {
        for (Customer customer : customers){
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }


    public void updateClaim(Claim claim) {
        claimInterface.update(claim);
    }
    public void deleteClaim(Claim claim) {
        claimInterface.delete(claim);
    }
    Claim viewClaim(String claimID) {return claimInterface.getOne(claimID);}

    public Set<Claim> viewAllClaims() {
        return claimInterface.getAll();
    }
    
    public Claim getClaim(String claimID) {
        return claimProcessManager.getOne(claimID);
    }


    public void mainMenuApp(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            systemView.displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    systemView.newClaimMenu();
                    break;
                case 2:
                    systemView.updateClaimMenu();
                    break;
                case 3:
                    systemView.deleteClaimMenu();
                    break;
                case 4:
                    systemView.viewAllClaimMenu();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
    

    public String generateCardNumber() { // Generate a random card number
        Random random = new Random();
        long cardNumber;
        do {
            cardNumber = (long) (random.nextDouble() * 10000000000000000L);
        } while (isCardNumExists(String.valueOf(cardNumber)));
        return String.valueOf(cardNumber);
    }

    public boolean isCardNumExists(String cardNumber) { // Check if the card number already exists
        for (Customer customer : customers) {
            if (customer.getInsurancecard() != null && customer.getInsurancecard().getCardNum().equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }

}
