package org.uzigula;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by umamani on 03/02/2015.
 */
// usando Stub = SImular o verificar estado (datos)
public class AuthorizationServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private IUserStore userStoreStub;
    private AuthorizationService auth;

    private User getUser() {
        return new User() {{userName="jperez"; password="1234567".hashCode();}};
    }

    @Before
    public void setUp() throws Exception {
        userStoreStub = mock(IUserStore.class);
        auth = new AuthorizationService(userStoreStub);
    }

    @Test
    public void Login_SendAValidUserPassword_ShouldBeAuthorized() throws InvalidLoginException {

        when(userStoreStub.getUser("jperez")).thenReturn(getUser());

        String token = auth.Login("jperez", "1234567");

        assertEquals("Should get a valid Token",32,token.length());
    }

    @Test
    public void Login_SendAnInvalidUserPassword_ShouldGetAnInvalidLogonException() throws InvalidLoginException {
        expectedEx.expect(InvalidLoginException.class);
        expectedEx.expectMessage("Invalid UserName/Password");

        when(userStoreStub.getUser("jperez")).thenReturn(getUser());

        String token = auth.Login("jperez","0987662");

    }

    @Test
    public void Login_SendAnNoExistentUserName_ShouldReturnAnInvalidLoginException() throws InvalidLoginException {
        expectedEx.expect(InvalidLoginException.class);
        expectedEx.expectMessage("User not found");

        when(userStoreStub.getUser("jperez")).thenReturn(null);

        String token = auth.Login("jperez","65431");

    }

/*    @Test
    public void Login_SendABlockedValidUser_ShouldReturnAn_(){

    }*/
}
