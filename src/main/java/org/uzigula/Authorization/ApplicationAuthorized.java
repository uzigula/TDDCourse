package org.uzigula.Authorization;

/**
 * Created by umamani on 04/02/2015.
 */
public class ApplicationAuthorized {
    public  String level;
    private int Id;
    public String applicationName;

    public ApplicationAuthorized(int id, String application, String accessLevel) {
        Id = id;
        applicationName = application;
        level = accessLevel;
    }
}
