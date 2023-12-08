package removeInsuranceObligation;

import insuranceManagementSystem.Command;
import insuranceManagementSystem.InsuranceManagementSystem;

import java.util.Scanner;

public class RemoveInsuranceObligationCommand implements Command {
    private InsuranceManagementSystem insuranceSystem;

    public RemoveInsuranceObligationCommand(InsuranceManagementSystem insuranceSystem) {
        this.insuranceSystem = insuranceSystem;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть індекс страхового зобов'язання, яке ви хочете видалити:");
        int indexToRemove = scanner.nextInt() - 1;
        insuranceSystem.removeInsuranceObligation(indexToRemove);
    }
}
