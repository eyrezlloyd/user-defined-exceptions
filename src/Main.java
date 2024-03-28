// Custom exception for exceeding maximum login attempts
class MaxLoginAttemptsException extends Exception {
    public MaxLoginAttemptsException(String message) {
        super(message);
    }
}

// Custom exception for invalid password
class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

// Class representing user login functionality
class UserLogin {
    private static final String VALID_PASSWORD = "password";
    private static int loginAttempts = 0;

    public static void login(String username, String password) throws MaxLoginAttemptsException, InvalidPasswordException {
        if (loginAttempts >= 3) {
            throw new MaxLoginAttemptsException("Maximum login attempts exceeded.");
        }

        if (!password.equals(VALID_PASSWORD)) {
            loginAttempts++;
            throw new InvalidPasswordException("Invalid password.");
        }

        System.out.println("Login successful!");
    }
}

// Main class to test the user login functionality
public class Main {
    public static void main(String[] args) {
        try {
            // Test invalid password
            UserLogin.login("user1", "wrongpassword");
        } catch (MaxLoginAttemptsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidPasswordException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            // Test exceeding maximum login attempts
            UserLogin.login("user2", "password");
            UserLogin.login("user2", "wrongpassword");
            UserLogin.login("user2", "wrongpassword");
            UserLogin.login("user2", "wrongpassword");
        } catch (MaxLoginAttemptsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidPasswordException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
