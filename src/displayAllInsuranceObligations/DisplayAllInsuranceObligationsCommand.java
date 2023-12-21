package displayAllInsuranceObligations;

import insuranceManagementSystem.Command;
import insuranceManagementSystem.InsuranceManagementSystem;

public class DisplayAllInsuranceObligationsCommand implements Command {
    private InsuranceManagementSystem insuranceSystem;

    public DisplayAllInsuranceObligationsCommand(InsuranceManagementSystem insuranceSystem) {
        this.insuranceSystem = insuranceSystem;
    }

    @Override
    public void execute() {
        insuranceSystem.displayAllInsuranceObligations();
    }
}
