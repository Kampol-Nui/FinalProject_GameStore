package account;

import person.Person;
import java.util.Objects;

public abstract class Account {

    private String username;
    private String password;
    private AccountStatus status;
    //private Person person;

//    public Account(String username, String password, Person person) {
//        this.username = username;
//        this.password = password;
//        this.status = AccountStatus.ACTIVE;
//        this.person = person;
//    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

 

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
