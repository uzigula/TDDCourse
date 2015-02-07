package org.uzigula.Authorization;

import java.util.List;

/**
 * Created by umamani on 03/02/2015.
 */
public class UserStore implements IUserStore{
    @Override
    public User getUser(String userName) {
        // abrirre la conexion
        // lanzare un comando que traiga un registro de la bd con el parametro username
        // mapear el registro a la clase User

        return null;
    }

    @Override
    public User getUserFromEmail(String emailAddress) {
        return null;
    }

    @Override
    public List<ApplicationAuthorized> getApplicationsFor(String s) {
        return null;
    }
}
