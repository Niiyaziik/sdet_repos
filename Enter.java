public class Enter {
    public static boolean validate(String login, String password, String confirmPassword) {
        try {
            if (!isValidLogin(login)) {
                throw new WrongLoginException("Login must contain only Latin letters, digits, and underscores, and must be less than 20 characters.");
            }

            if (!isValidPassword(password, confirmPassword)) {
                throw new WrongPasswordException("Password must contain only Latin letters, digits, and underscores, must be less than 20 characters, and must match confirmPassword.");
            }

            return true;

        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static boolean isValidLogin(String login) {
        return login != null && login.matches("^[a-zA-Z0-9_]{1,19}$");
    }
    private static boolean isValidPassword(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword) &&
               password.matches("^[a-zA-Z0-9_]{1,19}$");
    }
}
