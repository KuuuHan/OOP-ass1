package Controller;

/**
 * @Author Han Duc Khang - s3986602
 */

import Module.Customer;
import Module.Claim;
import Module.Bank;
import Module.Dependent;
import Module.PolicyHolder;
import Module.ClaimStatus;
import Module.InsuranceCard;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class FileHandler {
    public static void writeToFileCustomers(String fileLink, List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLink))) {
            for (Customer customer : customers) {
                String line = String.join(",", customer.getId(), customer.getName(),
                        customer.getInsuranceID(),
                        String.join(";", customer.getClaims().stream().map(Claim::getClaimID).collect(Collectors.toList())), customer instanceof PolicyHolder ? "PolicyHolder" : "Dependent", customer instanceof PolicyHolder ? String.join(";", ((PolicyHolder) customer).getDependents().stream().map(Dependent::getId).collect(Collectors.toList())) : "");
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }

    public static void writeToFileBank(String fileLink, List<Bank> banks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLink))) {
            for (Bank bank : banks) {
                String line = String.join(",", bank.getAccountNumber(), bank.getBankName(), bank.getAccountHolderName());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }

    public void writeToFileClaim(String fileLink, List<Claim> claims) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(fileLink), true))) {
            for (Claim claim : claims) {
                writer.print(claim.getClaimID() + ",");                           // 1. Claim ID
                writer.print(claim.getClaimDate() + ",");                         // 2. Claim Date
                writer.print(claim.getClaimAmount() + ",");                       // 3. Claim Amount
                writer.print(claim.getClaimStatus() + ",");                       // 4. Claim Status
                writer.print(claim.getExamDate() + ",");                          // 5. Claim exam Date
                writer.print(claim.getInsuredPersonID() + ",");                    // 6. Insured Person ID (customer ID)
                writer.print(claim.getInsuranceCardID()+ ",");                  //  7. Claimant InsuranceCard (customer insurance card)
                writer.print(claim.getReceiverBank().getAccountNumber() + ",");  //  8. Claimant Bank Name
                writer.print(String.join(";", claim.getDocuments()));            // 10. List of proving documents of the claim
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }
    
    public void writeToFileInsuranceCard(String fileLink, List<InsuranceCard> insuranceCards) {
        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLink))) {
            for (InsuranceCard insuranceCard : insuranceCards) {
                String line = String.join(",", insuranceCard.getCardNumber(), insuranceCard.getCardHolder(), insuranceCard.getPolicyOwner(), dfm.format(insuranceCard.getExpirationDate()));
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save to file: " + e.getMessage());
        }
    }


    public static List<Customer> readFromFileCustomer (String fileLink){
        List<Customer> customers = new ArrayList<>();
        Map<String, Dependent> dependents = new HashMap<>();
        fileLink = "customer.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileLink))) {
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
    public List<Claim> readFromFileClaim(String fileLink) {
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("claim.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 8) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String claimID = parts[0];                                                              // 1. Claim ID
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);             // 2. Claim Date
                double claimAmount = Double.parseDouble(parts[2]);                                      // 3. Claim Amount
                ClaimStatus claimStatus = ClaimStatus.valueOf(parts[3]);                                                          // 4. Claim Status
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);              // 5. Claim exam Date
                String insuredPersonId = parts[5];                                                      // 6. Insured Person ID (customer ID)
                String insuranceCardNumber = parts[6];                                                  // 7. Claimant InsuranceCard (customer insurance card)
                Bank receiverBank = new Bank(parts[7], parts[8], parts[9] );                            // 8. Claimant Bank Name
                List<String> documents = Arrays.asList(parts[10].split(";"));                     // 10. List of proving documents of the claim

                Claim claim = new Claim(claimID, claimDate, examDate, claimAmount, claimStatus, insuredPersonId, insuranceCardNumber, receiverBank, documents);

            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while trying to read from file: " + e.getMessage());
        }
        return claims;
    }

    public List<Bank> readFromFileBank(String fileLink) {
        List<Bank> banks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bank.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String accountNumber = parts[0]; //1. Bank Account Number
                String bankName = parts[1]; // 2. Bank Name
                String accountHolderName = parts[2]; // 3. Account Holder Name

                Bank bank = new Bank(accountNumber, bankName, accountHolderName);
                banks.add(bank);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to read from file: " + e.getMessage());
        }
        return banks;
    }

    public void ClaimsToCustomers(List<Customer> customers, List<Claim> claims) { // Link claims to customers (java APIs)
        for (Claim claim : claims) {
            Customer matchingCustomer = customers.stream()
                    .filter(customer -> customer.getId().equals(claim.getInsuredPersonID()))
                    .findFirst()
                    .orElse(null);
            if (matchingCustomer != null) {
                matchingCustomer.getClaims().add(claim);
            }
        }
    }

    public void CustomersToBanks(List<Customer> customers, List<Bank> banks) {
        for (Customer customer : customers) {
            Bank matchingBank = banks.stream()
                    .filter(bank -> bank.getAccountNumber().equals(customer.getInsurancecard().getCardNum()))
                    .findFirst()
                    .orElse(null);
            if (matchingBank != null) {
                customer.setBank(matchingBank);
            }
        }
    }


    public List<InsuranceCard> readFromFileInsuranceCard(String fileLink) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader("insuranceCard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String cardNumber = parts[0]; // 1. Card Number
                String cardHolder = parts[1]; // 2. Card Holder
                String policyOwner = parts[2]; // 3. Policy Owner
                Date expirationDate = sdf.parse(parts[3]); // 4. Expiration Date

                InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
                insuranceCards.add(insuranceCard);
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while trying to read from file: " + e.getMessage());
        }
        return insuranceCards;
    }
}


