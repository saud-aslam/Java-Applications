package ca.jrvs.apps.twitter.example;

public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+")) {
            sum = sum + Integer.valueOf(summand);
        }
        return sum;
    }

}
