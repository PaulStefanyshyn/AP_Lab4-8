package sortInsuranceByRisk;

import insuranceManagementSystem.Command;
import insuranceManagementSystem.InsuranceManagementSystem;

public class SortInsuranceItemsByRisk implements Command {
    private InsuranceManagementSystem insuranceSystem;

    public SortInsuranceItemsByRisk(InsuranceManagementSystem insuranceSystem) {
        this.insuranceSystem = insuranceSystem;
    }

    @Override
    public void execute() {
        insuranceSystem.sortInsuranceItemsByRisk();
        System.out.println("Страхові зобов'язання відсортовано за рівнем ризику.");
        // Додаткова логіка або виведення інформації, якщо необхідно
    }
}
