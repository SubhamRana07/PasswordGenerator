import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Password Generator");

        int length = getLengthFromUser(scanner);
        boolean useLowercase = getYesNoFromUser(scanner, "Include lowercase letters? (y/n): ");
        boolean useUppercase = getYesNoFromUser(scanner, "Include uppercase letters? (y/n): ");
        boolean useDigits = getYesNoFromUser(scanner, "Include digits? (y/n): ");
        boolean useSpecialChars = getYesNoFromUser(scanner, "Include special characters? (y/n): ");

        String password = generatePassword(length, useLowercase, useUppercase, useDigits, useSpecialChars);
        System.out.println("Generated Password: " + password);
    }

    private static int getLengthFromUser(Scanner scanner) {
        int length;
        do {
            System.out.print("Enter password length (minimum 8): ");
            length = scanner.nextInt();
        } while (length < 8);
        return length;
    }

    private static boolean getYesNoFromUser(Scanner scanner, String message) {
        System.out.print(message);
        String input = scanner.next().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    private static String generatePassword(int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars) {
        StringBuilder password = new StringBuilder();
        String validChars = "";

        if (useLowercase) {
            validChars += LOWERCASE_CHARS;
        }
        if (useUppercase) {
            validChars += UPPERCASE_CHARS;
        }
        if (useDigits) {
            validChars += DIGITS;
        }
        if (useSpecialChars) {
            validChars += SPECIAL_CHARS;
        }

        if (validChars.isEmpty()) {
            System.out.println("No character types selected. Using lowercase letters by default.");
            validChars = LOWERCASE_CHARS;
        }

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            char randomChar = validChars.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
