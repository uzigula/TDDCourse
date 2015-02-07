package org.uzigula.Authorization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umamani on 03/02/2015.
 */

public class AuthorizationService {


    private IEmailService _emailService;
    private IUserStore _userStore;

    public AuthorizationService(IUserStore userStore, IEmailService emailService){
        _userStore =userStore;
        _emailService = emailService;

    }

    public String Login(String userName, String password) throws InvalidLoginException {
        User user = _userStore.getUser(userName);
        if (user==null) throw new InvalidLoginException("User not found");
        if (!user.IsValidPassword(password)) throw new InvalidLoginException("Invalid UserName/Password");
        if (user.IsBlocked())  throw new InvalidLoginException("User Account is blocked");

        return String.format("%1$32s","X");
    }

    public void GetResetPasswordLink(String emailAddress) throws InvalidEmailException {
        User user =_userStore.getUserFromEmail(emailAddress);
        if (user==null) throw new InvalidEmailException("Email address not found");
        _emailService.SendEmail(generateResetMail(emailAddress));

    }

    private MailMessage generateResetMail(final String emailAddress) {
        return new MailMessage() {{from="noreply@acme.com"; to=emailAddress; subject="Reset Password"; body="";}};
    }


    public List<ApplicationAuthorized> getApplicationsAuthorized(String userName) {

        List<ApplicationAuthorized> apps = new ArrayList<>();
        /// poner el codigo que elimine los duplicados
        List<ApplicationAuthorized> allApps=_userStore.getApplicationsFor(userName);

        //TODO implement this with lambdas
        /*for(ApplicationAuthorized app:allApps){
            if Collections.sort().binarySearch()
            apps.add(app);
        }*/
        return apps;

    }
}
