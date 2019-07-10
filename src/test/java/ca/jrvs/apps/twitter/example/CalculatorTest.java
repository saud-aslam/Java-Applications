package ca.jrvs.apps.twitter.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {


    private Calculator calculator;

    @BeforeClass
    public static void setupClass() {

    }

    @Before
    public void setup() {
        calculator = new Calculator();
    }


    @Test
    public void evaluateHappyPath() {
        int sum = calculator.evaluate("1+2");

        assertNotNull(sum);

        //approach 1
        if (sum == 3) {
            System.out.println("i'm happy");
        } else {
            throw new RuntimeException("Bad");
        }

        //approach 2
        assertTrue(3 == sum);

        //approach 3
        assertEquals(3, sum);

        assertEquals(2, sum);
    }

    @Test
    public void evaluateSadPath() {
        try {
            int sum = calculator.evaluate("1.1+2");
            fail();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}