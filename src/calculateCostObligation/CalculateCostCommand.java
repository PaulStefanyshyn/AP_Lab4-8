package calculateCostObligation;

import insuranceManagementSystem.Command;
import insuranceManagementSystem.InsuranceManagementSystem;

public class CalculateCostCommand implements Command {
    private InsuranceManagementSystem insuranceSystem;

    public CalculateCostCommand(InsuranceManagementSystem insuranceSystem) {
        this.insuranceSystem = insuranceSystem;
    }

    @Override
    public void execute() {
        double totalCost = insuranceSystem.calculateTotalCost();
        System.out.println("Вартість страхових зобов'язань: " + totalCost);
    }
}
