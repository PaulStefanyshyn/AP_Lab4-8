package insuranceManagementSystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class InsuranceManagementSystemTest {
    private InsuranceManagementSystem insuranceSystem= new InsuranceManagementSystem();

    @org.junit.jupiter.api.Test
    void addInsuranceObligation() {
        InsuranceObligation obligation = new InsuranceObligation("Test Obligation", 100.0, 3);
        insuranceSystem.addInsuranceObligation(obligation);

        List<InsuranceObligation> obligations = insuranceSystem.getObligations();
        assertEquals(1, obligations.size());
        assertEquals(obligation, obligations.get(0));
    }

    @org.junit.jupiter.api.Test
    void sortInsuranceItemsByRisk() {
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("High Risk", 200.0, 5));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Low Risk", 100.0, 2));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Medium Risk", 150.0, 3));

        insuranceSystem.sortInsuranceItemsByRisk();

        List<InsuranceObligation> sortedObligations = insuranceSystem.getObligations();
        assertEquals("Low Risk", sortedObligations.get(0).getName());
        assertEquals("Medium Risk", sortedObligations.get(1).getName());
        assertEquals("High Risk", sortedObligations.get(2).getName());

    }

    @org.junit.jupiter.api.Test
    void calculateTotalCost() {
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 1", 100.0, 3));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 2", 150.0, 2));

        double totalCost = insuranceSystem.calculateTotalCost();
        assertEquals(250.0, totalCost, 0.001);
    }

    @org.junit.jupiter.api.Test
    void findObligationsInParameters() {
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 1", 100.0, 3));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 2", 150.0, 2));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 3", 200.0, 4));

        List<InsuranceObligation> foundObligations = insuranceSystem.findObligationsInParameters(100.0, 200.0, 2, 3);
        assertEquals(2, foundObligations.size());
        assertEquals("Item 1", foundObligations.get(0).getName());
        assertEquals("Item 2", foundObligations.get(1).getName());

    }

    @Test
    void removeInsuranceObligation() {
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 1", 100.0, 3));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 2", 150.0, 2));
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Item 3", 200.0, 4));

        insuranceSystem.removeInsuranceObligation(1);

        List<InsuranceObligation> remainingObligations = insuranceSystem.getObligations();
        assertEquals(2, remainingObligations.size());
        assertEquals("Item 1", remainingObligations.get(0).getName());
        assertEquals("Item 3", remainingObligations.get(1).getName());

    }

    @Test
    void readObligationsFromFile() {
        insuranceSystem.readObligationsFromFile("testObligations.txt");
        List<InsuranceObligation> obligations = insuranceSystem.getObligations();
        assertEquals(3, obligations.size());

    }

    @Test
    void rewriteObligationsToFile() {
        insuranceSystem.addInsuranceObligation(new InsuranceObligation("Test Item", 150.0, 3));
        insuranceSystem.rewriteObligationsToFile("testObligations.txt");

        // Перезавантажте об'єкт, щоб переконатися, що дані правильно перезаписані
        insuranceSystem = new InsuranceManagementSystem();
        insuranceSystem.readObligationsFromFile("testObligations.txt");
        List<InsuranceObligation> obligations = insuranceSystem.getObligations();
        assertEquals(1, obligations.size());
        assertEquals("Test Item'", obligations.get(0).getName());

    }
}