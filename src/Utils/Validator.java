package Utils;

public class Validator {
    public static boolean isValidUsername(String username) {
        return !username.isBlank();
    }

    public static boolean isValidPassword(String password) {
        return !password.isBlank();
    }
}
