package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//tests for Budget class
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

        //normal + less than 0 case
        assertTrue(budget.addBudget(100));
        assertFalse(budget.addBudget(-10));

        assertEquals(200, budget.getBudget());
    }

    @Test
    public void testLowerBudget() {

        //normal case
        assertTrue(budget.lowerBudget(50));
        assertEquals(50, budget.getBudget());


        //less than 0 or under budget case
        assertFalse(budget.lowerBudget(100));
        assertFalse(budget.lowerBudget(-10));
    }

    @Test
    public void testAddSpent() {

        //normal case
        assertEquals(0, budget.getSpent());
        budget.addSpent(10);
        assertEquals(10, budget.getSpent());
        assertEquals(90, budget.getRemaining());

        //less than 0 case
        assertFalse(budget.addSpent(-10));
    }

}

