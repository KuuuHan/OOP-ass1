package Controller;

import Module.Claim;
import Module.ClaimProcess;
import View.SystemView;
import Module.Customer;
import Controller.FileHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

/**
 * @Author Han Duc Khang - s3986602
 */
public class ClaimController {
    private ClaimProcess claimProcess;
    private SystemView systemView;
    private List<Customer> customers;
    public ClaimController(ClaimProcess claimProcess, SystemView systemView, List<Customer> customers) {
        this.claimProcess = claimProcess;
        this.systemView = systemView;
        this.customers = customers;
    }
    public void setSystemView(SystemView systemView) {this.systemView = systemView;}
    public void addClaim(Claim claim) {
        claimProcess.add(claim);
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
        claimProcess.update(claim);
    }
    public void deleteClaim(Claim claim) {
        claimProcess.delete(claim);
    }
    Claim viewClaim(String claimID) {return claimProcess.getOne(claimID);}

    public void viewAllClaims() {
        systemView.displayAll(claimProcess.getAll());
    }


    public void readFile(String fileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimID = parts[0];
                String claimDate = parts[1];
                double claimAmount = Double.parseDouble(parts[2]);
                String claimStatus = parts[3];
                String insuredPerson = parts[4];
                String bankName = parts[5];
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to read the file: " + e.getMessage());
        }
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
