package org.uzigula.Authorization;

import java.util.List;

/**
 * Created by umamani on 03/02/2015.
 */
public interface IUserStore {
    User getUser(String userName);

    User getUserFromEmail(String emailAddress);

    List<ApplicationAuthorized> getApplicationsFor(String s);
}
