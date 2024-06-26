package View;
/**
 *  This class is responsible for managing the list of claims and customers in the Claim Management System.
 *  It provides methods to add, update, delete, and view claims and customers.
 * @Author Han Duc Khang - s3986602
 */

import java.text.SimpleDateFormat;
import java.util.*;

import Controller.ClaimController;
import Module.Claim;
import Module.ClaimStatus;
import Module.Bank;
import Module.Customer;
import Module.InsuranceCard;

public class SystemView {
    private ClaimController controller;
    private Scanner scanner;

    public SystemView( ClaimController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() { // display
        System.out.println(" ");
        System.out.println("====== WELCOME TO CLAIM MANAGEMENT SYSTEM ======");
        System.out.println("       --------------------------------");
        System.out.println("Please choose the following options:");
        System.out.println("1. Add a new claim");
        System.out.println("2. Update a claim");
        System.out.println("3. Delete a claim");
        System.out.println("4. Display all claims");
        System.out.println("5. Exit");
        System.out.println("Please enter your choice: ");
    }

    //SUB FUNCTION
    private InsuranceCard getOrCreateInsuranceCard(Customer insuredPerson) {
        InsuranceCard card = insuredPerson.getInsurancecard();
        String cardNumber;
        String cardHolder = null;
        String policyOwner = null;
        Date expirationDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (card == null) {
            System.out.println("You don't have an insurance card. A new one will be created for you.");
            cardNumber = controller.generateCardNumber();
        } else {
            do {
                System.out.println("Enter your insurance card number: ");
                cardNumber = scanner.nextLine();
                if (!cardNumber.equals(card.getCardNum())) {
                    System.out.println("The card number you entered is incorrect. Please try again!");
                }
            } while (!cardNumber.equals(card.getCardNum()));
            System.out.println("Enter your insurance card expiration date (yyyy-MM-dd): ");
            String expirationDateString = scanner.nextLine();
            try {
                expirationDate = formatter.parse(expirationDateString);
            } catch (Exception e) {
                System.out.println("Invalid date format");
            }
            cardHolder = insuredPerson.getName();
            policyOwner = insuredPerson.getName();
        }
        card = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
        return card;
    }



    //MENU FUNCTION
    public void newClaimMenu() { // 1. create new claim
        System.out.println("======= New claim menu =======");

        System.out.println("Enter claim ID (f-xxxxxxxxxx): ");
        String claimID = scanner.nextLine();
        while (!claimID.matches("f-\\d{10}")) {
            System.out.println("Invalid claim ID. Please try again.");
            claimID = scanner.nextLine();
        }

        Date claimDate = new Date(); //get current date, set system date as claim date

        System.out.println("Enter exam date (yyyy-MM-dd): "); //get exam date
        String examDateString = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date examDate = null;
        try {
            examDate = formatter.parse(examDateString);
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }

        double claimAmount = 0;
        do{
            System.out.println("Enter claim amount: "); //get claim amount
            claimAmount = scanner.nextDouble();
            scanner.nextLine();
            if(claimAmount < 0){
                System.out.println("Invalid amount. Please try again.");
            }
        }while(claimAmount < 0);



        ClaimStatus status = ClaimStatus.New; //set status to new


        //working with insurance card
        Customer insuredPerson;
        do {
            System.out.print("Enter Insured Person's ID: ");
            System.out.print("Please enter as follow format c-xxxxxxx(7 digit):");
            String insuredPersonId = scanner.nextLine();

            insuredPerson =  controller.getCustomerById(insuredPersonId);
            if (insuredPerson == null) System.out.println("Insured Person does not exist!");
        } while (insuredPerson == null);

        String cardNumber = insuredPerson.getId();

        List<String> documents = new ArrayList<>();
        String documentName;
        do {
            System.out.print("Enter document name (or type 'none' if you dont have one): ");
            documentName = scanner.nextLine();
            if (!documentName.equals("none")) {
                String documentFileName = scanner + "_" + cardNumber + "_" + documentName + ".pdf";
                documents.add(documentFileName);
            }
        } while (!documentName.equals("done"));


        System.out.println("Banking Info:");
        System.out.print("Enter bank Name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter owner Name: ");
        String ownerName = scanner.nextLine();
        System.out.print(" Enter account Number: ");
        String accountNumber = scanner.nextLine();

        Bank bank = new Bank(bankName, ownerName, accountNumber);

        Claim newClaim = new Claim(claimID, claimDate, examDate, claimAmount, status, insuredPerson.getId(), cardNumber, bank, documents);

        insuredPerson.addCustomerClaim(newClaim);
        controller.addClaim(newClaim);

    }

    public void updateClaimMenu() { // 2. update claim
        System.out.println("======= Update claim menu =======");
        System.out.println("Enter claim ID: ");
        String claimID = scanner.nextLine();
        Claim claim = controller.getClaim(claimID);

        if (claim == null) {
            System.out.println("There is no claim with such ID. Please try again.");
            return;
        }

        Customer customer = controller.getCustomerById(claim.getCustomer().getId());
        customer.getClaims().remove(claim); //remove claim from customer's claim list

        System.out.println("Enter exam date (yyyy-MM-dd): "); //get exam date
        String examDateString = scanner.nextLine();
        Date examDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            examDate = formatter.parse(examDateString);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        System.out.println("Enter claim amount: "); //get claim amount
        double claimAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter status(New/Processing/Done): "); //get status
        String status = scanner.nextLine();

        claim.setExamDate(examDate);
        claim.setClaimAmount(claimAmount);
        claim.setStatus(status);

        customer.getClaims().add(claim); //add claim back to customer's claim list
        System.out.println("Claim updated successfully...");
    }

    public void deleteClaimMenu() { // 3. delete claim
        System.out.println("======= Delete claim menu =======");
        System.out.println("Enter claim ID that you want to delete: ");
        String claimID = scanner.nextLine();
        Claim claim = controller.getClaim(claimID);

        if (claim == null) {
            System.out.println("There is no claim with such ID. Please try again.");
            return;
        }

        Customer customer = controller.getCustomerById(claim.getCustomer().getId());
        customer.getClaims().remove(claim); //remove claim from customer's claim list
        controller.deleteClaim(claim);
        System.out.println("Claim deleted successfully...");
    }

    public void viewAllClaimMenu() { // 4. view all claims
        Set<Claim> all = controller.viewAllClaims();
        System.out.println("======= All claims menu =======");
        if (all.isEmpty()) {
            System.out.println("There is no claim to display.");
        } else {
            for (Claim claim : all) {
                System.out.println(claim);
            }
        }
    }

}

