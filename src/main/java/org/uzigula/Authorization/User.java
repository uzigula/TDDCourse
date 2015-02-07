package org.uzigula.Authorization;

/**
 * Created by umamani on 03/02/2015.
 */
public class User {

    public User (String username, int password, boolean blocked){
        userName=username;
        _password = password;
        _blocked =blocked;
    }

    private int _password;
    public String userName;
    private boolean _blocked;

    public boolean IsBlocked() {
        return _blocked;
    }
    public boolean IsValidPassword(String passwordTry) {
        return (_password ==passwordTry.hashCode());
    }
}
