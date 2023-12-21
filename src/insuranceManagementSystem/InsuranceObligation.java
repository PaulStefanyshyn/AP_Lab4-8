package insuranceManagementSystem;

public class InsuranceObligation {
    private String name;
    private double cost;
    private int riskLevel;

    public InsuranceObligation(String name, double cost, int riskLevel) {
        this.name = name;
        this.cost = cost;
        this.riskLevel = riskLevel;
    }
    public InsuranceObligation(String line) {
        String[] parts = line.split(","); // Припустимо, що дані розділені комою
        if (parts.length == 3) {
            this.name = parts[0].trim();
            this.cost = Double.parseDouble(parts[1].trim());
            this.riskLevel = Integer.parseInt(parts[2].trim());

        } else {
            throw new IllegalArgumentException("Неправильний формат рядка для створення об'єкта InsuranceObligation");
        }
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", " + cost +
                ", " + riskLevel;
    }
}
