import Controller.FileHandler;
import Module.ClaimProcessManager;
import View.SystemView;
import Controller.ClaimController;
import Module.Claim;
import Module.InsuranceCard;
import Module.Bank;
import Module.Customer;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();

        List<Customer> customers = handler.readFromFileCustomer("src/customers.txt");
        List<Claim> claims = handler.readFromFileClaim("src/claims.txt");
        List<Bank> banks = handler.readFromFileBank("src/bank.txt");
        List<InsuranceCard> insuranceCards = handler.readFromFileInsuranceCard("src/insuranceCard.txt");
        handler.ClaimsToCustomers(customers, claims);
        handler.CustomersToBanks(customers, banks);

        ClaimProcessManager manager = new ClaimProcessManager(customers, claims);
        ClaimController controller = new ClaimController(manager, null, customers);
        SystemView view = new SystemView(controller);
        controller.setSystemView(view);
        controller.mainMenuApp();

        handler.writeToFileCustomers("src/customers.txt", manager.getCustomers());
        handler.writeToFileClaim("src/claims.txt", manager.getClaims());
        handler.writeToFileBank("src/bank.txt", banks);
        handler.writeToFileInsuranceCard("src/insuranceCard.txt", insuranceCards);
    }
}
