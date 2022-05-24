package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidUsername(String username) {
        return username != null && !username.isBlank() && !username.contains(" ");
    }

    public static boolean isValidPassword(String password) {
        return password != null && !password.isBlank() && !password.contains(" ");
    }

    public static boolean isValidName(String name) {
        if (name == null)
            return false;
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static void main(String[] args) {
        System.out.println(isValidUsername("Test nè"));
    }
}
