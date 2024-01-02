package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        // Change password only if the oldPassword is equal to the current password
        // and the new password meets all of the specified criteria

        String currentPassword = getPassword();

        if (oldPassword.equals(currentPassword)) {
            String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$";

            if (newPassword.matches(passwordRegex)) {
                this.password = newPassword;
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Password change failed. Please check the new password criteria.");
            }
        } else {
            System.out.println("Password change failed. Old password does not match.");
        }
    }

    // Additional methods can be added as needed
}
