package Utils;

import java.util.StringTokenizer;
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
        StringTokenizer tokenizer = new StringTokenizer(name, " ", false);
        while (tokenizer.hasMoreTokens())
            if (Character.isLowerCase(tokenizer.nextToken().charAt(0)))
                return false;
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static boolean isValidEmail(String mail) {
        if (mail == null)
            return false;
        String regx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.matches(regx, mail);
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null)
            return false;
        if (phone.length() == 10) {
            if (phone.startsWith("0")){
                String regex = ".*[a-zA-Z].*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcherText = pattern.matcher(phone);
                return !matcherText.matches();
            }
        }
        return false;
    }

    public static boolean isValidAddress(String address) {
        if (address == null)
            return false;
        String regx = "^[0-9]{1,}[a-zA-Z]*[/0-9A-Za-z]*([a-zA-Z]|[\\s])*";
        return Pattern.matches(regx, address);
    }

    public static void main(String[] args) {
        System.out.println(isValidPhone("0397361223"));
    }
}
