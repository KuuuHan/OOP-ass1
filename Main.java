import Module.ClaimProcessManager;
import View.SystemView;
import Controller.ClaimController;
import Module.Claim;
import Module.InsuranceCard;
import Module.Bank;


import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManager();
        ClaimController controller = new ClaimController(claimProcessManager, null);
        SystemView view = new SystemView(controller);
        controller.setSystemView(view);
    }
}
