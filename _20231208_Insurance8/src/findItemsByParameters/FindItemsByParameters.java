package findItemsByParameters;

import insuranceManagementSystem.Command;
import insuranceManagementSystem.InsuranceManagementSystem;
import insuranceManagementSystem.InsuranceObligation;

import java.util.List;
import java.util.Scanner;

public class FindItemsByParameters implements Command {
    private InsuranceManagementSystem insuranceSystem;

    public FindItemsByParameters(InsuranceManagementSystem insuranceSystem) {
        this.insuranceSystem = insuranceSystem;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть мінімальну вартість:");
        double minCost = scanner.nextDouble();
        System.out.println("Введіть максимальну вартість:");
        double maxCost = scanner.nextDouble();
        System.out.println("Введіть мінімальний рівень ризику:");
        int minRisk = scanner.nextInt();
        System.out.println("Введіть максимальний рівень ризику:");
        int maxRisk = scanner.nextInt();

        List<InsuranceObligation> foundItems = insuranceSystem.findObligationsInParameters(minCost, maxCost, minRisk, maxRisk);

        System.out.println("Знайдені страхові зобов'язання за параметрами:");
        for (InsuranceObligation obligation : foundItems) {
            System.out.println("Назва: " + obligation.getName() +
                    ", Вартість: " + obligation.getCost() +
                    ", Рівень ризику: " + obligation.getRiskLevel());
        }
    }
}
