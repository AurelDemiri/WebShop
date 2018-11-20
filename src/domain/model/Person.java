package domain.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public Person(String userId, String email, String password, String firstName, String lastName) {
        setUserId(userId);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("No userId given");
        }

        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }

        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }

        this.password = password;
    }

    public void setPasswordHashed(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }

        this.password = hashPassword(password);
    }

    public boolean isPasswordCorrect(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }

        return getPassword().equals(hashPassword(password));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            byte[] digest = crypt.digest();
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            return digestAsBigInteger.toString(16);
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("No first name given");
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }

        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserId() + ", " + getEmail();
    }
}
