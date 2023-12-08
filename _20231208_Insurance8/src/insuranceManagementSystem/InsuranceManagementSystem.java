package insuranceManagementSystem;

import addInsuranceObligation.AddInsuranceObligationCommand;
import calculateCostObligation.CalculateCostCommand;
import displayAllInsuranceObligations.DisplayAllInsuranceObligationsCommand;
import findItemsByParameters.FindItemsByParameters;
import invoke.Invoker;
import removeInsuranceObligation.RemoveInsuranceObligationCommand;
import sortInsuranceByRisk.SortInsuranceItemsByRisk;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Основний клас програми
public class InsuranceManagementSystem implements Command {
    public void execute() {
        // Логіка розрахунку вартості страхового зобов'язання
        System.out.println("Викликано команду для розрахунку вартості страхового зобов'язання.");
    }

    private List<InsuranceObligation> obligations;

    public InsuranceManagementSystem() {
        this.obligations = new ArrayList<>();
    }

    public void addInsuranceObligation(InsuranceObligation obligation) {
        obligations.add(obligation);
        System.out.println("Додано новий вид страхового зобов'язання: " +
                "Назва: " + obligation.getName() +
                ", Вартість: " + obligation.getCost() +
                ", Рівень ризику: " + obligation.getRiskLevel());
    }

    // Сортування страхових зобов'язань за рівнем ризику
    public void sortInsuranceItemsByRisk() {
        obligations.sort(Comparator.comparingInt(InsuranceObligation::getRiskLevel));

        // Вивести відсортований список страхових зобов'язань
        System.out.println("Відсортований список страхових зобов'язань за рівнем ризику:");
        for (InsuranceObligation obligation : obligations) {
            System.out.println("Назва: " + obligation.getName() +
                    ", Вартість: " + obligation.getCost() +
                    ", Рівень ризику: " + obligation.getRiskLevel());
        }
    }

    // Розрахунок вартості страхових зобов'язань
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (InsuranceObligation obligation : obligations) {
            totalCost += obligation.getCost();
        }
        return totalCost;
    }

    // Знаходження зобов'язань за параметрами
    public List<InsuranceObligation> findObligationsInParameters(double minCost, double maxCost, int minRisk, int maxRisk) {

        List<InsuranceObligation> result = new ArrayList<>();
        for (InsuranceObligation obligation : obligations) {
            if (obligation.getCost() >= minCost && obligation.getCost() <= maxCost &&
                    obligation.getRiskLevel() >= minRisk && obligation.getRiskLevel() <= maxRisk) {
                result.add(obligation);
            }
        }
        return result;
    }

    // Видалення страхового зобов'язання за індексом
    public void removeInsuranceObligation(int index) {
        if (index >= 0 && index < obligations.size()) {
            InsuranceObligation removedObligation = obligations.remove(index);
            System.out.println("Видалено страхове зобов'язання: " + removedObligation.getName());
        } else {
            System.out.println("Невірний індекс для видалення страхового зобов'язання.");
        }
    }

    // Відображення всіх страхових зобов'язань
    public void displayAllInsuranceObligations() {
        if (obligations.isEmpty()) {
            System.out.println("Немає страхових зобов'язань для відображення.");
        } else {
            System.out.println("Усі страхові зобов'язання:");
            int index = 0;
            for (InsuranceObligation obligation : obligations) {
                index++;
                System.out.println("Індекс: " + index +
                        ", Назва: " + obligation.getName() +
                        ", Вартість: " + obligation.getCost() +
                        ", Рівень ризику: " + obligation.getRiskLevel());
            }
        }
    }

    // Запис усіх страхових зобов'язань у файл
    public void writeObligationsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.newLine();

            for (InsuranceObligation obligation : obligations) {
                writer.write(obligation.toString()); // Припустимо, що у вашому класі InsuranceObligation є toString() метод
                writer.newLine();
            }
            System.out.println("Зобов'язання успішно записані у файл " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл " + fileName);
            e.printStackTrace();
        }
    }

    // Зчитування страхових зобов'язань з файлу
    public void readObligationsFromFile(String fileName) {
        obligations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Пропускаємо перший рядок
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // Припустимо, що у вашому класі InsuranceObligation є конструктор, який приймає рядок для ініціалізації об'єкта
                InsuranceObligation obligation = new InsuranceObligation(line);
                obligations.add(obligation);
            }
            System.out.println("Зобов'язання успішно зчитані з файлу " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні з файлу " + fileName);
            e.printStackTrace();
        }
    }

    // Перезапис страхових зобов'язань у файл, починаючи з другого рядка
    public void rewriteObligationsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            // Пропускаємо запис для першого рядка
            writer.newLine();

            for (InsuranceObligation obligation : obligations) {
                writer.write(obligation.toString());
                writer.newLine();
            }
            System.out.println("Зобов'язання успішно перезаписані у файл " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл " + fileName);
            e.printStackTrace();
        }
    }
    // Отримати список страхових зобов'язань
    public  List<InsuranceObligation> getObligations() {
        return obligations;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Invoker invoker = new Invoker();
        InsuranceManagementSystem insuranceSystem = new InsuranceManagementSystem();
        insuranceSystem.readObligationsFromFile("obligations7.txt");
        while (true) {
            // Вивід меню
            System.out.println("Оберіть опцію:");
            System.out.println("1. Додати новий вид страхового зобов'язання");
            System.out.println("2. Розрахувати вартість страхового зобов'язання");
            System.out.println("3. Сортувати страхові зобов'язання за рівнем ризику");
            System.out.println("4. Знайти зобов'язання за параметрами");
            System.out.println("5. Видалити страхове зобов'язання");
            System.out.println("6. Переглянути усі страхові зобов'язання");
            System.out.println("0. Вихід");

            // Обробка вводу користувача
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    invoker.setCommand(new AddInsuranceObligationCommand(insuranceSystem));
                    invoker.executeCommand();
                    insuranceSystem.writeObligationsToFile("obligations7.txt");
                    break;
                case 2:
                    invoker.setCommand(new CalculateCostCommand(insuranceSystem));
                    invoker.executeCommand();
                    break;
                case 3:
                    invoker.setCommand(new SortInsuranceItemsByRisk(insuranceSystem));
                    invoker.executeCommand();
                    break;
                case 4:
                    invoker.setCommand(new FindItemsByParameters(insuranceSystem));
                    invoker.executeCommand();
                    break;
                    case 5:
                    invoker.setCommand(new RemoveInsuranceObligationCommand(insuranceSystem));
                    invoker.executeCommand();
                    insuranceSystem.rewriteObligationsToFile("obligations7.txt");
                    break;
                    case 6:
                        invoker.setCommand(new DisplayAllInsuranceObligationsCommand(insuranceSystem));
                        invoker.executeCommand();
                    break;
                case 0:
                    System.out.println("Програма завершує роботу.");
                    System.exit(0);
                default:
                    System.out.println("Невідома команда. Спробуйте ще раз.");
            }
        }
    }
}
