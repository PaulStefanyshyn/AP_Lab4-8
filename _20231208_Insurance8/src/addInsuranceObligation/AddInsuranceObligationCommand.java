package addInsuranceObligation;

import insuranceManagementSystem.Command;
import insuranceManagementSystem.InsuranceManagementSystem;
import insuranceManagementSystem.InsuranceObligation;

import java.util.Scanner;

// Конкретні команди
public class AddInsuranceObligationCommand implements Command {
    private InsuranceManagementSystem insuranceSystem;

    public AddInsuranceObligationCommand(InsuranceManagementSystem insuranceSystem) {
        this.insuranceSystem = insuranceSystem;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву нового зобов'язання:");
        String name = scanner.nextLine();
        System.out.println("Введіть вартість нового зобов'язання:");
        double cost = scanner.nextDouble();
        System.out.println("Введіть рівень ризику нового зобов'язання:");
        int riskLevel = scanner.nextInt();

        InsuranceObligation newObligation = new InsuranceObligation(name, cost, riskLevel);
        insuranceSystem.addInsuranceObligation(newObligation);
    }
}
