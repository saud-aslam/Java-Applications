package ca.jrvs.apps.twitter.example;


public class ValiDationID {
    public static boolean isAlpha(String s) {
        return s != null && s.chars().noneMatch(character -> character < '0' || character > '9');
    }

    public static void main(String[] args) {
        String s = "1234567";
        System.out.println("IsAlpha: " + isAlpha(s));
    }
}
