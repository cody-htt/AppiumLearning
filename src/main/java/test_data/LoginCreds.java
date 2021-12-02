package test_data;

public class LoginCreds {

    private String email;
    private String password;

    public LoginCreds(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCreds{" +
               "username='" + email + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
