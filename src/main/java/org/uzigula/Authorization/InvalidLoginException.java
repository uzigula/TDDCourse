package org.uzigula.Authorization;

/**
 * Created by umamani on 03/02/2015.
 */
public class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}
