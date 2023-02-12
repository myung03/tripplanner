package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetTest {

    private Budget budget;

    @BeforeEach
    public void setUp() {
        budget = new Budget(100.0);
    }

    @Test
    public void testAddBudget() {
        //test getter
        assertEquals(100, budget.getBudget());

        budget.addBudget(100);

        assertEquals(200, budget.getBudget());

    }

    @Test
    public void testLowerBudget() {
        budget.lowerBudget(50);
        assertEquals(50, budget.getBudget());

    }

    @Test
    public void testAddSpent() {
        assertEquals(0, budget.getSpent());

        budget.addSpent(10);
        assertEquals(10, budget.getSpent());
        assertEquals(90, budget.getRemaining());
    }

}

