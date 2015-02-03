package org.uzigula;

/**
 * Created by umamani on 03/02/2015.
 */

public class AuthorizationService {


    private IUserStore _userStore;

    public AuthorizationService(IUserStore userStore){
        _userStore =userStore;
    }

    public String Login(String userName, String password) throws InvalidLoginException {
        User user = _userStore.getUser(userName);
        if (user==null) throw new InvalidLoginException("User not found");
        if (IsValidPassword(password, user)) throw new InvalidLoginException("Invalid UserName/Password");
        return String.format("%1$32s","X");
    }

    private boolean IsValidPassword(String password, User user) {
        return user.password!=password.hashCode();
    }
}
