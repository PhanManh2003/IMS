package entity.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String username;
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + '}';
    }
    
}
