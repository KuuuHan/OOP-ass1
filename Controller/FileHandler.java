package Controller;

/**
 * @Author Han Duc Khang - s3986602
 */

import Module.ClaimProcess;
import View.SystemView;
import Module.Customer;
import Module.Claim;
import Module.Bank;
import Module.Dependent;
import Module.PolicyHolder;
import Module.InsuranceCard;
import Module.ClaimProcessManager;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class FileHandler {
    public void writeToFileCustomer(Customer customer) {
        String fileName = "customer.txt";
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(fileName), true))) {
            writer.print(customer.getId() + ",");                         // ID
            writer.print(customer.getName() + ",");                       // Customer
            writer.print(customer.getInsurancecard().getCardNum() + ","); // InsuranceCard
            for (Claim claim : customer.getClaims()) {                    // List of Claim ids
                writer.print(claim.getClaimID() + ",");
            }
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }

    public void writeToFileBank(Bank bank) {
        String fileName = "bank.txt";
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(fileName), true))) {
            writer.print(bank.getAccountNumber() + ",");                         //1. Bank Account Number
            writer.print(bank.getBankName() + ",");                             // 2. Bank Name
            writer.print(bank.getAccountHolderName() + ",");                    // 3. Account Holder Name
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }

    public void writeToFileClaim(Claim claim) {
        String fileName = "claim.txt";
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(fileName), true))) {
            writer.print(claim.getClaimID() + ",");                           // 1. Claim ID
            writer.print(claim.getClaimDate() + ",");                         // 2. Claim Date
            writer.print(claim.getClaimAmount() + ",");                       // 3. Claim Amount
            writer.print(claim.getClaimStatus() + ",");                       // 4. Claim Status
            writer.print(claim.getExamDate() + ",");                          // 5. Claim exam Date
            writer.print(claim.getInsuredPerson().getId() + ",");             // 6. Insured Person ID (customer ID)
            writer.print(claim.getInsurancecard().getCardNum() + ",");       //  7. Claimant InsuranceCard (customer insurance card)
            writer.print(claim.getReceiverBank().getAccountNumber() + ",");  //  8. Claimant Bank Name

            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }


    public static List<Customer> readFromFileCustomer (String fileName){
        List<Customer> customers = new ArrayList<>();
        Map<String, Dependent> dependents = new HashMap<>();
        fileName = "customer.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String id = parts[0];                                                     // ID
                String name = parts[1];                                                   // Customer
                String cardNumber = parts[2];                                             // InsuranceCard
                String customerType = parts[3];                                           // Customer Type
                List<String> claimIds = Arrays.asList(parts[4].split(";"));         // List of Claim ids split by ";"

                if ("PolicyHolder".equals(customerType)) {
                    List<Dependent> dependentList = new ArrayList<>();
                    if (parts.length >= 6 && parts[5] != null && !parts[5].isEmpty()) { // If there are dependents
                        String[] dependentIds = parts[5].split(";");
                        for (String dependentId : dependentIds) {                       // Add dependent to the list
                            if (dependents.containsKey(dependentId)) {
                                dependentList.add(dependents.get(dependentId));
                            }
                        }
                    }
                    PolicyHolder policyHolder = new PolicyHolder(id, name, cardNumber , claimIds, dependentList);
                    customers.add(policyHolder);
                } else if ("Dependent".equals(customerType)) {
                    Dependent dependent = new Dependent(id, name, cardNumber, claimIds);
                    dependents.put(id, dependent);
                } else {
                    System.out.println("Invalid customer type: " + customerType);
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while trying to read from file: " + e.getMessage());
        }

        return customers;
    }

}


