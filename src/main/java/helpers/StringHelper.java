package helpers;

public class StringHelper {

    public static String reverseString(String text) {
        String reversedString = new StringBuilder(text).reverse().toString();
        return reversedString;
    }
}