package model;

public class LoginResult {
    private boolean success;
    private String role;

    public LoginResult(boolean success, String role) {
        this.success = success;
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRole() {
        return role;
    }
}

